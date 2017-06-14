#lang pl

;; Oz Levi - 305181158 - Ex04

;;Question1.a

;; Defining two new types
(define-type BIT = (U 0 1))
(define-type Bit-List = (Listof BIT))     
 
;; The actual interpreter
#| BNF for the ROL language:
<ROL>  ::= { reg-len = <num> <RegE>}

<RegE> ::= {<Bits>} |
           {and <RegE> <RegE>} |
           {or <RegE> <RegE>} |
           {shl <RegE>} |
           {id '<sym>} |
           {with {'<sym> <RegE>} <RegE>} |
           {call <RegE> <RegE>} |
           {fun '<sym> <RegE>}

<Bits> ::= null |
           {<Bit>} |
           {<Bit> <Bits>}

<Bit> ::= 0|1
|# 

(define-type RegE
  [Reg Bit-List]
  [And RegE RegE]
  [Or RegE RegE]
  [Shl RegE]
  [Id Symbol]
  [With Symbol RegE RegE]
  [Call RegE RegE]
  [Fun Symbol RegE])

;; Next is a technical function that converts (casts)
;; (any) list into a bit-list. We use it in parse-sexpr.
(: list->bit-list : (Listof Any) -> Bit-List)
;; to cast a list of bits as a bit-list
(define (list->bit-list lst)
  (cond [(null? lst) null]
        [(eq? (first lst) 1)(cons 1 (list->bit-list (rest lst)))]
        [else (cons 0 (list->bit-list (rest lst)))])) 

(: parse-sexpr : Sexpr -> RegE)
;; to convert the main s-expression into ROL
(define (parse-sexpr sexpr)
  (match sexpr
    [(list 'reg-len '= len exp)
     (if (number? len)
         (if (< len 1)
             (error 'parse-sexpr "Register length must be at least 1 ~s" sexpr) ;; remember to make sure specified register length is at least 1
             (parse-sexpr-RegL exp len))
         (error 'parse-sexpr "bad syntax in ~s" sexpr))] 
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))
 
(: parse-sexpr-RegL : Sexpr Number -> RegE)
;; to convert s-expressions into RegEs
(define (parse-sexpr-RegL sexpr reg-len)
  (match sexpr
    [(list (and a (or 1 0)) ... )(if (eq? (length (list sexpr)) reg-len)
                                     (Reg (list->bit-list (list sexpr)))
                                     (error 'parse-sexpr-RegL "wrong number of bits in ~s" a))]
    [(list 'and fst sec) (And (parse-sexpr-RegL fst reg-len) (parse-sexpr-RegL sec reg-len))]
    [(list 'or fst sec) (Or (parse-sexpr-RegL fst reg-len) (parse-sexpr-RegL sec reg-len))]
    [(list 'shl fst) (Shl (parse-sexpr-RegL fst reg-len))]
    [(cons 'with more) (match sexpr
                         [(list 'with (list (symbol: var) val) exp) (With var (parse-sexpr-RegL val reg-len) (parse-sexpr-RegL exp reg-len))]
                         [else (error 'parse-sexpr-RegL "bad `with' syntax in ~s" sexpr)])]
    [(cons 'fun more) (match sexpr
                        [(list 'fun (list (symbol: var)) exp) (Fun var (parse-sexpr-RegL exp reg-len))]
                        [else (error 'parse-sexpr-RegL "bad `fun' syntax in ~s" sexpr)])]
    [(list 'call lexp rexp) (Call (parse-sexpr-RegL lexp reg-len) (parse-sexpr-RegL rexp reg-len))]
    [else (error 'parse-sexpr-RegL "bad syntax in ~s" sexpr)]))
 
(: parse : String -> RegE)
;; parses a string containing a RegE expression to a RegE AST
(define (parse str)
  (parse-sexpr (string->sexpr str))) 

(: subst : RegE Symbol RegE -> RegE)
;; substitutes the second argument with the third argument in the
;; first argument, as per the rules of substitution; the resulting
;; expression contains no free instances of the second argument 
(define (subst expr from to)
  (cases expr
    [(Reg n) expr]
    [(And l r) (And (subst l from to) (subst r from to))]
    [(Or l r) (Or (subst l from to) (subst r from to))]
    [(Shl l) (Shl (subst l from to))]
    [(Id name) (if (eq? name from) to expr)]
    [(With bound-id named-expr bound-body)
     (With bound-id (subst named-expr from to)
           (if (eq? bound-id from)
               bound-body
               (subst bound-body from to)))]
    [(Call l r)
     (Call (subst l from to) (subst r from to))]
    [(Fun bound-id bound-body)
     (if (eq? bound-id from)
         expr
         (Fun bound-id (subst bound-body from to)))]))

(: eval : RegE -> RegE)
;; evaluates RegE expressions by reducing them to RegE that is
;; of the two variants (Reg..) or (Fun..)
(define (eval expr)
  (cases expr
    [(Reg n) n]
    [(And l r) (reg-arith-op and-arith-op (eval l) (eval r))]
    [(Or l r) (reg-arith-op or-arith-op (eval l) (eval r))]
    [(Shl l) (shl-arith-op (eval l))]
    [(With bound-id named-expr bound-body) (eval (subst bound-body bound-id (eval named-expr)))]
    [(Id name) (error 'eval "free identifier: ~s" name)]
    [(Fun bound-id bound-body) expr]
    [(Call fun-expr arg-expr) (let ([fval (eval fun-expr)])
                                (cases fval
                                  [(Fun bound-id bound-body) (eval (subst bound-body bound-id (eval arg-expr)))]
                                  [else (error 'eval "`call' expects a function, got: ~s" fval)]))]))
  
(: reg-arith-op : (BIT BIT -> BIT) RegE RegE -> RegE)
;; Consumes two registers and some binary bit operation 'op',
;; and returns the register obtained by applying op on the
;; i'th bit of both registers for all i.
(define (reg-arith-op op reg1 reg2)
  (cases reg1
    [(Reg bits1) (cases reg2
                    [(Reg bits2) (Reg (bit-arith-op op bits1 bits2))]
                    [else (reg-arith-op op reg1 (eval reg2))])]
    [else (cases reg2
            [(Reg bits2) (reg-arith-op op (eval reg1) reg2)]
            [else (reg-arith-op op (eval reg1) (eval reg2))])]))

(: bit-arith-op : (BIT BIT -> BIT) Bit-List Bit-List -> Bit-List)
;; pffffff im tierdddd
(define (bit-arith-op op lbits rbits)
  (cons (op (first lbits) (first rbits)) (bit-arith-op op (rest lbits) (rest rbits))))

( : and-arith-op : (BIT BIT -> BIT))
(define (and-arith-op first second)
  (if(eq? first second) first 0))

( : or-arith-op : (BIT BIT -> BIT))
(define (or-arith-op first second)
  (if(eq? first second) 1 first))

(: shl-arith-op : RegE -> Bit-List)
(define (shl-arith-op bits)
  (cases bits
    [(Reg b) (append (rest b) (list (first b)))]
    [else (shl-arith-op (eval bits))]))

(: run : String -> Bit-List)
;; evaluate a ROL program contained in a string 
(define (run str)
  (let ([result (eval (parse str))])
    (cases result
      [(Reg n) n]
      [else (error 'run "evaluation returned a non-number: ~s" result)])))

;; tests
(test (run "{ reg-len =  4  {1 0 0 0}}") => '(1 0 0 0))
(test (run "{ reg-len = 4  {shl {1 0 0 0}}}") => '(0 0 0 1))
(test (run "{ reg-len = 4  {and {shl {1 0 1 0}}{shl {1 0 1 0}}}}") => '(0 1 0 1))
(test (run "{ reg-len = 4  { or {and {shl {1 0 1 0}} {shl {1 0 0 1}}} {1 0 1 0}}}") => '(1 0 1 1))
(test (run "{ reg-len = 2  { or {and {shl {1 0}} {1 0}} {1 0}}}") => '(1 0))
(test (run "{ reg-len = 4  {with {x {1 1 1 1}} {shl y}}}") =error> "free identifier")
(test (run "{ reg-len = 2  { with {x { or {and {shl {1 0}} {1 0}} {1 0}}}
                {shl x}}}") => '(0 1))
(test (run "{ reg-len = 4  {or {1 1 1 1} {0 1 1}}}") =error> "wrong number of bits in")
(test (run "{ reg-len =  0  {}}") =error> "Register length must be at least 1")
(test (run "{ reg-len = 3
                  {with {identity {fun {x} x}}
                     {with {foo {fun {x} {or x {1 1 0}}}}
                        {call {call identity foo} {0 1 0}}}}}"))