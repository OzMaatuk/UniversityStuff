#lang pl


;;Q1.a
#|
<LE> ::= <num>|<SEXPR>|<APPEND>|<CONSEXPR>
<SEXPR> ::='<sym>
<APPEND> ::={append <APPEND><APPEND>}|{list <LIST><APPEND>}|<CONSEXPR><APPEND>|λ
<CONSEXPR> ::= {cons <num><CONSEXPR>}|{cons <num><APPEND>}|{cons <num><null>}|
               {cons <SEXPR><CONSEXPR>}|{cons <SEXPR><APPEND>}|{cons <SEXPR><null>}|
               {cons <APPEND><CONSEXPR>}|{cons <APPEND><APPEND>}|{cons <APPEND><null>}|
               {cons <CONSEXPR><CONSEXPR>}|{cons <CONSEXPR><APPEND>}|{cons <CONSEXPR><null>}
<LIST> ::= {list <LIST>}|<SEXPR><LIST>|<num><LIST>|λ
|#

;;Q1.b
#|
zafrir yakir
(cons (append (cons 'zaf (list 'rir)) (list 'yak 'ir)) null)
<LE> => <CONSEXPR>(0) => {cons <APPEND>(0)<null>}
<APPEND>(0) => <CONSEXPR>(1)<APPEND>(1)
<CONSEXPR>(1) => {cons <SEXPR>(0)<APPEND>(2)}
<SEXPR>(0) => 'zaf
<APPEND>(2) => {list <LIST>(0)<APPEND>(3)}
<LIST>(0) => <SEXPR> => 'rir
<APPEND>(3) =>λ
<APPEND>(1) => {list <LIST>(1)<APPEND>(4)}
<LIST>(1) => <SEXPR> => 'yak
<APPEND>(4) => {list <LIST>(2)<APPEND>(5)}
<LIST>(2) => <SEXPR> => 'ir
<APPEND>(5) =>λ

|#


;;Q2
(: sequence : ( All (A)(A -> A) A A -> (Listof A)))
(: sequenceList : ( All (A)(A -> A) A A (Listof A) -> (Listof A)))


(define (sequence f first last)
  (sequenceList f first last (list first)))


(define (sequenceList f first last lst)
  (if (eq? first last)
      lst
      (sequenceList f (f first) last (append lst (list(f first))))))


(test (sequence add1 1 1) => (list 1)) 
(test (sequence add1 1 5) => (list 1 2 3 4 5))
(test (sequence sub1 5 1) => (list 5 4 3 2 1))
(test (sequence sqrt 65536 2) =>  (list 65536 256 16 4 2))
(test (sequence not #f #t) => (list #f #t))
(test (sequence (inst rest Number) (list 1 2 3) null) => (list (list 1 2 3) (list 2 3) (list 3) null))


;;Q3.1
(define-type INTSET
  [Int   Integer]
  [Range  Integer Integer]
  [2Sets  INTSET INTSET])

(: intset-normalized?  : ( INTSET -> Boolean))
(define (intset-normalized?  intSet)
  (cases intSet
    [(Int num)
     (number? num)]
    [(Range x1 x2)
     (if (< x1 x2) #t #f)]
    [(2Sets intSet1 intSet2)
     (if (< (intset-max intSet1)(sub1 (intset-min intSet2)))
         (and (intset-normalized? intSet1) (intset-normalized? intSet2))
                           #f)]))

(: intset-min : INTSET -> Integer)
;; Finds the minimal member of the given set.
(define (intset-min set)
  (intset-min/max set <))

(: intset-max : INTSET -> Integer)
;; Finds the maximal member of the given set.
(define (intset-max set)
  (intset-min/max set >))

(: intset-min/max : INTSET (Integer Integer -> Boolean)  -> Integer)
(define (intset-min/max set operator)
  (cases set
    [(Int num) num]
    [(Range x1 x2)(if(operator x1 x2) x1 x2)]
    [(2Sets intSet1 intSet2)
     (if(operator (intset-min/max intSet1 operator)(intset-min/max intSet2 operator)) (intset-min/max intSet1 operator) (intset-min/max intSet2 operator))] ))

(test (intset-max (Range 1 90)) => 90)
(test (intset-min (Range 1 90)) => 1)
(test (intset-max (Int  93)) => 93)
(test (intset-min/max (2Sets (Int  2) (Int  3)) <) => 2)
(test (intset-min/max (2Sets (Range 1 90) (Int  93)) <) => 1)
(test (intset-min/max (2Sets (Range 1 90) (Int  93)) >) => 93)
(test (intset-normalized? (2Sets(Int  2) (Int  5))) => #t)
(test (intset-normalized? (2Sets (Range 1 90) (Int 93))) => #t)
(test (intset-normalized? (2Sets(Int  2) (Int  3))) => #f)
(test (intset-normalized? (2Sets(Int  6) (Int  3))) => #f)
(test (intset-normalized? (Range 1 90)) => #t)
(test (intset-normalized? (Range 8 6)) => #f)
(test (intset-normalized? (Int  6)) => #t)


;;Q3.2
#|
INTSET ::= <int>|{<int> - <int>}|{<INTSET><INTSET>}
|#