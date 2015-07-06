; clojure中可以定义namespace 方法 (ns namespace-name)
; 如何引入namespace　(use 'namespace-name)
; 或者 ( require  '(clojure [ xml :as xml-core ]) )
; use 和 require的区别：require在使用引入的package的时候 需要写全名

; reload package ( ns 'my-package :reload )

; namespace 相关函数
; ns-resolve resolve ( ns-resolve namespace symbol )
; 如果这个symbol在namespace中找到，那么会返回改对象

; destructing
(defn describe-salary [person]
	(let [ first (:first-name person)
		last (:last-name person)
		annual (:salary person) ]
		(println first last " earns " annual)))
; 也可以这么写
(defn describe-salary2 [ { first :first-name
						last :last-name
						annual :salary } ]
		(println first last " earns " annual))

; vector-binding 类是于scala的case语句
(defn print-amounts [ [amount1 amount2] ]
	( println "amounts are " amount1 " and " amount2 ))
(print-amounts [10.0 11.0])

; 另外可以使用 & 来引用多余参数 :as xxx 来引用所有的参数
(defn print-amount-multiple [ [amount1 amount2 & remaining :as all] ]
	(println "amounts are " amount1 "," amount2 "and" remaining )
	(println "also all the amounts are:" all))
(print-amount-multiple [1 2 3 4 5 6 7 8])

; & _ 忽略剩余的binding
(defn print-first-category [ [category amount] & _]
	(println "first category was" category)
	(println "first amount was" amount))
(def expenses [ [:books 49.95] [:coffee 4.95] [:caltrain 2.25] ])
(print-first-category expenses)

; map binding or提供默认参数
(defn describe-salary-3 [ { first :first-name
							last :last-name
							annual :salary
							bouns :bouns-percentage :or { bouns 5 } } ]
	( println first last "earns" annual "with a" bouns "percent bouns" ))
(def another-user { :first-name "basic"
					:last-name "groovy"
					:salary 10000 })
(describe-salary-3 another-user)

; map binding 中也可以使用:as 引用整个参数
(defn describe-person [ { first :first-name
						last :last-name
						bouns :bouns :or { bouns 5 } 
						:as p} ]
	(println "info about" first last "is:" p ))
(def third-user { :first-name "fist"
					:last-name "last"
					:salary 600 })
(describe-person third-user)

; 另外的解耦方式 使用:keys :syms :strs
(defn greet-user [ {:keys [first-name last-name]} ]
	(println "welcome," first-name last-name))
(def roger { :first-name "roger" :last-name "mann" :salary 600 })
(greet-user roger)
; output welcome, roger man

