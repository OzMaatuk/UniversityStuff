;; 305181158

#lang pl

;; Question 1.1
;; the ready function string makes a string from charecters
(: append5 : Char Char Char Char Char -> String)
;; append5 gets 5 charecters
;; and output a string
;; which is all the chars together
(define (append5 a b c d e)
  	(string a b c d e))
(test (append5 #\e #\d #\c #\b #\a) => "edcba")
(test (append5 #\a #\2 #\c #\D #\^) => "a2cD^")

;; Question 1.2
;; the function makes any combination of 3 charecters as string
;; by creating all the posible options
(: permute3 : Char Char Char -> (List String String String String String String))
;; permute3 gets 3 chrecters
;; and output a list of strings
(define (permute3 a b c)
  (list (string a b c) (string b a c) (string b c a) (string c b a) (string c a b) (string a c b)))
;; this is more efficien to solve this by placing the chars then recursivly
(test (permute3 #\a #\b #\c) =>'("abc" "bac" "bca" "cba" "cab" "acb"))
(test (permute3 #\1 #\r #\^) =>'("1r^" "r1^" "r^1" "^r1" "^1r" "1^r"))

;; Question 2
;; the function recursuvly go over evety element in the list
;; use ready length function for checking if it list of three elements
;; counts every time that it equal
(: count-3lists : (Listof(Listof Any)) ->  Integer)
;; count-3lists gets a list of lists with any value
;; and output the number of inner lists that got 3 elements
(define (count-3lists ls)
  (if(null? ls)
     0
     (if(eq? (length(first ls)) 3)
         (+ 1(count-3lists(rest ls)))
         (+ 0 (count-3lists(rest ls))))))
(test (count-3lists '((1 3 4) (() (1 2 3)) ("tt" "Three" 7) (2 4 6 8) (1 2 3))) => 3)
(test (count-3lists '((1 3) ((1 #\d 1) (1 "f" 3) (1)) ("ttt" "Three" 7) (2 4 6 8 7) (1 2 3))) => 3)

;; Queation 3
;; the solution includes two functions,
;; one is calling the other
;; and adding another input as our counter for the recursivly process
(: compute-poly : Number (Listof Number) -> Number)
;; gets a number as x and a list of coefficients which numbers too
;; outputs the solution to the equation as where asked
(: innet-compute-poly : Number (Listof Number) Number -> Number)
;; gets a number as x and a list of coefficients which numbers too,
;; a value of x^i which start with 1 and comp by x every recursive loop
;;
(define(compute-poly x list)
  (innet-compute-poly x list 1))

(define (innet-compute-poly x list expo)
  (if(null? list)
     0
     (+ (* (first list) expo)(innet-compute-poly x (rest list) (* x expo)))))
(test (compute-poly 2 '(6 2 3)) => 22)
(test (compute-poly 3 '(0 -2 3 4)) => 129)


;; Question 5


(: is-odd? : Natural -> Boolean)
;; the function gets a natural number
;; and return a boolean value true if the input in an odd number, else return false.
;; first, checking if the natural number is zero and reply as well,
;; else it will use another function is-even? which decrease the input and check if is even.
;; return a boolean answare as followed.
(define (is-odd? x)
 (if (zero? x)
 false
 (is-even? (- x 1))))
(: is-even? : Natural -> Boolean)
;; the function gets a natural number
;; and return a boolean value true if the input in an even number, else return false
;; first, checking if the natural number is zero and reply as well,
;; else it will use another function is-even? which decrease the input and check if is even.
;; return a boolean answare as followed.

(define (is-even? x)
 (if (zero? x)
 true
 (is-odd? (- x 1))))

;; the functions will help each other until a zero answare will appeare in the right function.

;; tests --- is-odd?/is-even?
(test (not (is-odd? 12)))
(test (is-even? 12))
(test (not (is-odd? 0)))
(test (is-even? 0))
(test (is-odd? 1))
(test (not (is-even? 1)))


(: every? : (All (A) (A -> Boolean) (Listof A) -> Boolean))
;; See explanation about the All syntax at the end of the file...
;; the function gets a boolean function and list
;; the function check if all elements in list returnd true for the referened function,
;; is those. retrun true, else false.

(define (every? pred lst)
;; calculate the next expression
 (or (null? lst) ;; OR we got to the end of the list then auto true
 (and (pred (first lst)) ;; or we will calculate the boolean value of our finction with the head of the list
 (every? pred (rest lst))))) ;; and make as AND for the boolean with the answare from the rest of the list
;; until we reach the last element and starting calculate the whole expression recursivly

;; An example for the usefulness of this polymorphic function
(: all-even? : (Listof Natural) -> Boolean)
;; the function gets a list
;; the function check if all elements in list are even,
;; is those. retrun true, else false.

(define (all-even? lst)
 (every? is-even? lst)) ;; using the every? function and is-even? function as input.

;; tests
(test (all-even? null))
(test (all-even? (list 0)))
(test (all-even? (list 2 4 6 8)))
(test (not (all-even? (list 1 3 5 7))))
(test (not (all-even? (list 1))))
(test (not (all-even? (list 2 4 1 6))))


(: every2? : (All (A B) (A -> Boolean) (B -> Boolean) (Listof A) (Listof B) ->
Boolean))
;; the function gets a two boolean functions and two lists
;; the function check if all elements in first list returnd true for the first referened function,
;; and function check if all elements in second list returnd true for the second referened function,
;; is those. retrun true, else false.

(define (every2? pred1 pred2 lst1 lst2)
 (or (null? lst1) ;; both lists assumed to be of same length,
     ;;calculate the next expression: OR we got to the end of the lists then auto true
 (and (pred1 (first lst1))  ;; OR we will calculate the boolean value of our first finction with the head of the first list AND
 (pred2 (first lst2)) ;; calculate the boolean value of our second finction with the head of the second list
 (every2? pred1 pred2 (rest lst1) (rest lst2))))) ;; and make as AND for the boolean with the answare from the rest of the two lists with the  same functions
;; until we reach the last and starting calculate the whole expression recursivly


;; Question 4
;; all this question was solved by the insperation of liat cohen cake
;; defining a new var type for the keyed-stack
;; with empty constructor
;; and push/next constructor which takes a symbol as node key, string for node valueת, and keyed-stack as next node
(define-type KeyStack
  [EmptyKS]
  [Push Symbol String KeyStack])

;; this section i asked the help of yoav shaibi

(: search-stack : Symbol KeyStack -> Any)
;; search-stack function which takes a symbol as a key, and a keyed-stack
;; the function check if the keyed-stack head (the current) symbol
;; is equal to the symbol key that we got in the input
;; if not, activate the function on the same key symbol input,
;; with the KeyStack (which refer to the next node) in the stack that we got as input.
;; the recursive operation will stop when the stack input that we got is EmptySK.
(define (search-stack index stack)
  (cases stack ;; case check for stack defenition
    [[Push K V S] ;; when it is Push type, that is args and
     (if (eq? index K) V ;; compare the symbol with the input, if equal we got our answare
     (search-stack index S))] ;; else call the function wit hthe same index and the next KeyStack (the KeyStack arg of Push)
    [else #f]));; case its not a Push type, return false.

(: pop-stack : KeyStack -> Any)
;; pop-stack gets a keyed-stack arg
;; and returns the "inner" stack (the next node)
;; if its empty, will return false.
(define (pop-stack stack)
  (cases stack ;; case check for stack defenition
    [[Push K V S] S] ;; when it is Push type return the KetStack arg of this Push.
    [else #f]));; case its not a Push type, then we empy and return false.


(test (EmptyKS) => (EmptyKS))
(test (Push 'b "B" (Push 'a "A" (EmptyKS))) =>  
      (Push 'b "B" (Push 'a "A" (EmptyKS)))) 
(test (Push 'a "AAA" (Push 'b "B" (Push 'a "A"  (EmptyKS)))) => (Push 'a "AAA" (Push 'b "B" (Push 'a "A" (EmptyKS))))) 
(test (search-stack 'a (Push 'a "AAA" (Push 'b "B" (Push 'a "A" (EmptyKS))))) => "AAA") 
(test (search-stack 'c (Push 'a "AAA" (Push 'b "B" (Push 'a "A" (EmptyKS))))) => #f) 
(test (pop-stack (Push 'a "AAA" (Push 'b "B" (Push 'a "A" (EmptyKS))))) => (Push 'b "B" (Push 'a "A" (EmptyKS))))