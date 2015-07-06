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
