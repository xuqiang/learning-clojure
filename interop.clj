; interop clojure和java的互操作

; clojure中调用java
; import package
; (import & import-symbols-or-lists)
(import 
	'(org.apache.hadoop.hbase.client HTable)
	'(org.apache.hadoop.hbase.client Scan))
; 更加推荐的import是配合ns使用
(ns com.clojureaction.book
	(:import (java.util  Set)))
; 创建java对象 调用对象方法
(import '(java.text SimpleDateFormat))
(def sdf (new SimpleDateFormat "yyyy-MM-dd"))
; 或者在类名之后 写. 和new类似
(def sdf2 (SimpleDateFormat. "yyyy-MM-dd"))

; 调用类的方法 .method-name object params
(defn date-from-date-string [ date-string ]
	(let [sdf (new SimpleDateFormat "yyyy-MM-dd")]
		(.parse sdf date-string)))