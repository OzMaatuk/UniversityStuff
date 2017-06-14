#lang pl

;;Q 1.1
;;found at Racket doc the function string that creates string from chars.
(: append5 : Char Char Char Char Char -> String)
(define (append5 a b c d e)
	(string a b c d e))
(test(append5 #\y #\a #\k #\i #\r) => "yakir")
(test (append5 #\e #\d #\c #\b #\a) => "edcba")

;;Q 1.2
;;create list of six strings that are the permutation
(: permute3 : Char Char Char -> (List String String String String String String))
(define (permute3 a b c)
  (list (string a b c) (string a c b) (string b a c) (string b c a) (string c a b) (string c b a)))
(test (permute3 #\a #\b #\c) =>'("abc" "acb" "bac" "bca" "cab" "cba"))

;;Q 2
;;i use the function list-length from the practice for check each list size.
;; the function count-3lists will use the list-length func for checking if the size 3 and adding if yes else continue until null
(: list-length : (Listof Any) -> Number)
(define (list-length ls)
  (if(null? ls)
     0
     (+ 1(list-length(rest ls)))))

(: count-3lists : (Listof (Listof Any)) -> Number)
(define (count-3lists lst)
   (if(null? lst)
      0
      (if(eq? (list-length(first lst)) 3)
         (+ 1(count-3lists(rest lst)))
         (+ 0 (count-3lists(rest lst))))))
(test (count-3lists '((1 3 4) (() (1 2 3)) ("tt" "Three" 7) (2 4 6 8) (1 2 3))) => 3)


;;Q 3
;;i use another recursive function so i can have n for exponent
;; and i check if the list isnt null i add a*x^n
;; so how expt function work from Racket docs.
(: compute-poly : Number (Listof Number) -> Number)
(: compute-poly1 : Number Integer (Listof Number) -> Number)

(define(compute-poly x list)
  (compute-poly1 x 0 list))

(define (compute-poly1 x i list)
  (if(null? list)
     0
     (+ (* (first list) (expt x i))(compute-poly1 x (+ 1 i) (rest list)))))

(test (compute-poly 2 '(6 2 3)) => 22)
(test (compute-poly 3 '(0 -2 3 4)) => 129)

;;Q 5
(: is-odd? : Natural -> Boolean)
;; the function input in a Natural number
;; the output is if the number is odd
;;if the number is 0 the number is even and return false else -1 to the number and check if is-even?
(define (is-odd? x)
 (if (zero? x)
 false
 (is-even? (- x 1))))
(: is-even? : Natural -> Boolean)
;; the function input in a Natural number
;; the output is if the number is even
;;if the number is 0 the number is even and return true else -1 to the number and check if is-odd?
(define (is-even? x)
 (if (zero? x)
 true
 (is-odd? (- x 1))))
;; tests --- is-odd?/is-even?
(test (not (is-odd? 12)))
(test (is-even? 12))
(test (not (is-odd? 0)))
(test (is-even? 0))
(test (is-odd? 1))
(test (not (is-even? 1)))



(: every? : (All (A) (A -> Boolean) (Listof A) -> Boolean))
;; See explanation about the All syntax at the end of the file...
;; << Add your comments here>>
;; << Add your comments here>>
(define (every? pred lst)
 (or (null? lst)
 (and (pred (first lst))
 (every? pred (rest lst)))))
;; An example for the usefulness of this polymorphic function
(: all-even? : (Listof Natural) -> Boolean)
;; << Add your comments here>>
;; << Add your comments here>>
(define (all-even? lst)
 (every? is-even? lst))
;; tests
(test (all-even? null))
(test (all-even? (list 0)))
(test (all-even? (list 2 4 6 8)))
(test (not (all-even? (list 1 3 5 7))))
(test (not (all-even? (list 1))))
(test (not (all-even? (list 2 4 1 6))))
(: every2? : (All (A B) (A -> Boolean) (B -> Boolean) (Listof A) (Listof B) ->
Boolean))
;; << Add your comments here>>
;; << Add your comments here>>
(define (every2? pred1 pred2 lst1 lst2)
 (or (null? lst1) ;; both lists assumed to be of same length
 (and (pred1 (first lst1))
 (pred2 (first lst2))
 (every2? pred1 pred2 (rest lst1) (rest lst2)))))
