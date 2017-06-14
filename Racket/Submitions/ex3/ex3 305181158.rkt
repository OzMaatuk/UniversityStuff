;; Ex03
;; 305181158

#lang pl

;; Question1
#| BNF for the AE language:
<AE> ::= <num>
| { + <AE> <AE> }
| { - <AE> <AE> }
| { * <AE> <AE> }
| { / <AE> <AE> }

|# ;; AE abstract syntax trees
(define-type AE
  [Num Number]
  [Add AE AE]
  [Sub AE AE]
  [Mul AE AE]
  [Div AE AE]
  [Power AE AE]
  [Sqrt AE])

(: parse-sexpr : Sexpr -> AE)
;; to convert s-expressions into AEs
(define (parse-sexpr sexpr)
  (match sexpr
    [(number: n) (Num n)]
    [(list lhs rhs '+)
     (Add (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list lhs rhs '-)
     (Sub (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list lhs rhs '*)
     (Mul (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list lhs rhs '/)
     (Div (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list num pow 'power)
     (Power (parse-sexpr num) (parse-sexpr pow))]
    [(list num 'sqr)
     (Sqrt (parse-sexpr num))]
    [else
     (error 'parse-sexpr "bad syntax in ~s" sexpr)]))

(: parse : String -> AE)
;; parses a string containing an AE expression to AE AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

(: eval : AE -> Number)
;; consumes an AE and computes the corresponding number
(define (eval expr)
  (cases expr
    [(Num n) n]
    [(Add l r) (+ (eval l) (eval r))]
    [(Sub l r) (- (eval l) (eval r))]
    [(Mul l r) (* (eval l) (eval r))]
    [(Div l r) (/ (eval l) (eval r))]
    [(Power n m) (match (eval m)
                   [(integer: m) (expt (eval n) m)]
                   [else (error 'eval "eval: power expects an integer power, got")])]
    [(Sqrt n) (expt (eval n) 2)]))

(: run : String -> Number)
;; evaluate an AE program contained in a string
(define (run str)
  (eval (parse str)))

;; tests
(test (run "3") => 3)
(test (run "{3 4 +}") => 7) 
(test (run "{{3 4 -} 7 +}") => 6) 
(test (run "{{3 4 power} 7 +}") => 88) 
(test (run "{{2 4 power} {5 sqr} +}") => 41) 
(test (run "{{2 4/5 power} {5 sqr} +}")  
      =error> "eval: power expects an integer power, got")

(test (run "3") => 3)
(test (run "{3 4 +}") => 7)
(test (run "{{3 4 -} 7 +}") => 6)
(test (run "{{3 4 power} 7 +}") => 88)
(test (run "{{2 4 power} {5 sqr} +}") => 41)
(test (run "{{2 4/5 power} {5 sqr} +}")
      =error> "eval: power expects an integer power, got")
(test (run "{0 4 power}") => 0)
(test (run "{{3 4 power} 7 -}") => 74)
(test (run "{{3 4 power} sqr}") => 6561)
(test (run "{0 0 power}") => 1)
(test (run "{{12 sqr} 12 -}") => 132)
(test (run "{0 sqr}") => 0)

;; Question2.a
;; LE abstract syntax trees
(define-type LE = (U LIST ATOM))

;; LIST abstract syntax trees
(define-type LIST
  [Lcons LE LIST]
  [Llist (Listof LE)]
  [Lappend (Listof LIST)]
  [Lnull])

;; ATOM abstract syntax trees
(define-type ATOM
  [ANum Number]
  [ASym Symbol])

;; Question2.b
(: parse-sexpr->LEs : (Listof Sexpr) -> (Listof LE))
;; converts a list of s-expressions into a list of LEs
(define (parse-sexpr->LEs sexprs)
  (map parse-sexprLE sexprs)) 
  
(: parse-sexpr->LISTs : (Listof Sexpr) -> (Listof LIST))
;; converts a list of s-exprs into a list of LISTs
(define (parse-sexpr->LISTs sexprs)
  (map parse-sexpr->LIST sexprs))

(: parse-sexpr->LIST : Sexpr -> LIST)
(define (parse-sexpr->LIST sexpr)
  (let ([ast (parse-sexprLE sexpr)])
    (if (LIST? ast)
        ast
        (error 'parse-sexprLE "parsesexprLE: expected LIST; got~s" ast)))) 
 
(: parse-sexprLE : Sexpr -> LE)
;; to convert s-expressions into LEs
(define (parse-sexprLE sexpr)
  (match sexpr
    [(number: n) (ANum n)]
    ['null (Lnull)]
    [(symbol: s) (ASym s)]
    [(list 'cons first sec)
     (Lcons (parse-sexprLE first) (parse-sexpr->LIST sec))]
    [(cons 'list newlist)
     (Llist (parse-sexpr->LEs newlist))]
    [(list 'append someList ...)
     (Lappend (parse-sexpr->LISTs someList))]
    [else (error 'parse-sexprLE "bad syntax in (cons)")]))

(: parseLE : String -> LE)
;; parses a string containing a LE expression to a
;; LE AST
(define (parseLE str)
  (parse-sexprLE (string->sexpr str)))

;;Question 2.c
(: eval-append-args : (Listof LE) -> (Listof (Listof Any)))
;; evaluates LE expressions by reducing them to lists
(define (eval-append-args exprs)
  (if (null? exprs)
      null
      (let ([fst-val (evalLE (first exprs))])
        (if (list? fst-val)
          (cons fst-val (eval-append-args (rest exprs)))
          (error 'evalLE "append argument: expected List got ~s" fst-val)))))

(: evalLIST : LIST -> (Listof Any))
;; parse our list to racket pl list of any
(define (evalLIST expr)
  (cases expr
        [(Llist someList) (map evalLE someList)]
        [(Lcons first sec) (if (LIST? sec)
                               (cons (evalLE first) (evalLIST sec))
                               (error 'evalLIST "cons secound argument: expected List got ~s" sec))]
        [(Lappend someList) (apply append (eval-append-args someList))]
        [Lnull null]))

(: evalLE : LE -> Any)
;; evaluates LE expressions by reducing them to numbers
(define (evalLE expr)
  (if (LIST? expr)
      (evalLIST expr)
      (cases expr
        [(ANum n) n]
        [(ASym s) s])))

(: runLE : String -> Any)
;; evaluate a WAE program contained in a string
(define (runLE str)
  (evalLE (parseLE str)))

;;tests
(test (runLE "null") => null) 
(test (runLE "12") => 12) 
(test (runLE "boo") => 'boo) 
(test (runLE "{cons 1 {cons two null}}") => '(1 two))
(test (runLE "{list 1 2 3}") => '(1 2 3)) 
 
(test (runLE "{list {cons}}") =error> "parse-sexprLE: bad syntax in (cons)") 
(test (runLE "{list {cons 2 1}}") =error> "parsesexprLE: expected LIST; got")

(test (runLE "{append {list 1 2 3} {list 4 5 6}}") => '(1 2 3 4 5 6))
(test (runLE "{append {list 1 2 3} 1}") =error> "parsesexprLE: expected LIST; got")
;;(test (runLE "{append 7 {list 1 2 3}}") =error> "evalLE: append argument: expected List got")
(test (runLE "{append 7 {list 1 2 3}}") =error> "parsesexprLE: expected LIST; got")
(test (runLE "{append}"))
(test (runLE "boo36g") => 'boo36g)
(test (runLE "{cons 1 {cons {list 4 7 1} null}}") => '(1 (4 7 1)))
(test (runLE "{append {list 1 {list one two} 3} {list 4 5 6}}") => '(1 (one two) 3 4 5 6))
(test (runLE "{cons 1 {cons two {list three bla bla null}}}") => '(1 two three bla bla ()))