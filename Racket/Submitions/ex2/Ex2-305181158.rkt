;; Ex02
;; 305181158
#lang pl

;; Question1.a
#|
;; defining the LE grammar by the original symbol and number,
;; adding three new sub-grammars as appendLE, consLE, listLE
;; for the three operation append, cons, list.
;; LE can be one of those eight.
<LE> ::=  Null|       	(0)
         '<sym>|      	(1)
          <num>|      	(2)
          <appendLE>| 	(3)
          <consLE>|   	(4)
          <listLE>|    	(5)
          <null>|       (6)
	  λ		(7)

;; defining the new listLE for the list operation
;; following the question rules
<listLE> ::= {list <LE> <PAIR>}	(8)
             {list <LE>}        (9)
             {list}	        (10)

;; defining a list object (pair or singal LE)
<PAIR> ::= {<LE> <PAIR>}|         (11)
           {<LE>}                 (12)

;; defining the new appendLE for the append operation
;; following the question rules
<appendLE> ::= {append <listLE> <LIST>}	(13)
               {append <listLE>}        	(14)
               {append}                 	(15)

;; defining a append object (pair or singal listLE)
<LIST> ::= {<listLE> <LIST>}|                   (16)
           {<listLE>}                           (17)

;; defining the new consLE for the cons operation
;; following the question rules
<consLE> ::= {cons <LE> <appendLE>}|	        (18)
             {cons <LE> <consLE>}|              (19)
             {cons <LE> <listLE>}|              (20)
             {cons <LE> <null>}                 (21)
|#

;; Question1.b
#|
let build the expression "305181158OzLevi"

{cons 305 {cons 181 {cons 158 {cons Oz {cons Le {cons vi null}}}}}}

{<LE>} (4)=> {<consLE>} ;; {305181158OzLevi} (4)=> {305181158OzLevi}
{<consLE>} (15)=> {cons <LE> <consLE>} ;; {305181158OzLevi} (15)=> {305 181158OzLevi}
{cons <LE> <consLE>} (2)=> {cons <num> <consLE>} ;; {305 181158OzLevi} (2)=> {305 181158OzLevi}
{cons <num> <consLE>} (15)=> {cons <num> {cons <LE> <consLE>}} ;; {305 181158OzLevi} (15)=> {305 181 158OzLevi}
{cons <num> {cons <LE> <consLE>}} (2)=> {cons <num> {cons <num> <consLE>}} ;; {305 181 158OzLevi} (2)=> {305 181 158OzLevi}
{cons <num> {cons <num> <consLE>}} (15)=> {cons <num> {cons <num> {cons <LE> <consLE>}}} ;; {305 181 158OzLevi} (15)=> {305 181 158 OzLevi}
{cons <num> {cons <num> {cons <LE> <consLE>}}} (2)=> {cons <num> {cons <num> {cons <num> <consLE>}}} ;; {305 181 158 OzLevi} (2)=> {305 181 158 OzLevi}
{cons <num> {cons <num> {cons <num> <consLE>}}} (15)=> {cons <num> {cons <num> {cons <num> {cons <LE> <consLE>}}}} ;; {305 181 158 OzLevi} (15)=> {305 181 158 Oz Levi}
{cons <num> {cons <num> {cons <num> {cons <LE> <consLE>}}}} (1)=> {cons <num> {cons <num> {cons <num> {cons '<sym> <consLE>}}}} ;; {305 181 158 Oz Levi} (2)=> {305 181 158 Oz Levi}
{cons <num> {cons <num> {cons <num> {cons '<sym> <consLE>}}}} (17)=> {cons <num> {cons <num> {cons <num> {cons '<sym> {cons <LE> <null>}}}}} ;; {305 181 158 Oz Levi} (17)=> {305 181 158 Oz Le vi}
{cons <num> {cons <num> {cons <num> {cons '<sym> {cons <LE> <null>}}}}} (1)=> {cons <num> {cons <num> {cons <num> {cons '<sym> {cons '<sym> <null>}}}}} ;; {305 181 158 Oz Levi} (1)=> {305 181 158 Oz Le vi}

|#

;; Question2
;; the function sequence getting some ready function and two arguments
;; the function will calculate a sequence of the input function between the first argument and the secound
(: sequence : ( All (A)(A -> A) A A -> (Listof A)))

;; the function runSequence getting some ready function and three arguments
;; two edges of the function result, and onr for the currect result
;; the result is the recusive level result of the f function calculated
;; the function will return a list of the results
;; (: runSequence : ( All (A)(A -> A) A A A -> (Listof A)))

;; the function is calling another recursive function with a result argument
(define (sequence f first last)
  (if (eq? first last)
      (list last)
      (append (list first) (sequence f (f first) last))))

;; tests
(test (sequence add1 1 1) => (list 1)) 
(test (sequence add1 1 5) => (list 1 2 3 4 5))
(test (sequence sub1 5 1) => (list 5 4 3 2 1))
(test (sequence sqrt 65536 2) =>  (list 65536 256 16 4 2))
(test (sequence not #f #t) => (list #f #t))
(test (sequence (inst rest Number) (list 1 2 3) null) => (list (list 1 2 3) (list 2 3) (list 3) null))
