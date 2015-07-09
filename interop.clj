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
; 如何调用静态方法
(Long/parseLong "12321")
; 使用class的静态成员
(import '(java.util Calendar))
(Calendar/JANUARY)

; java的链式调用
(import '(java.util Calendar TimeZone))
(. (. (Calendar/getInstance) (getTimeZone)) (getDisplayName) )
; 这样写起来比较麻烦 更加简化的写法
(.. (Calendar/getInstance) (getTimeZone) (getDisplayName) )
; 传入参数
(.. 
	(Calendar/getInstance)
	(getTimeZone)
	(getDisplayName true TimeZone/SHORT))

; doto宏 在一个ojbect上同时调用多个方法
(import '(java.util Calendar))
(defn the-past-midnight []
	(let [calendar-obj (Calendar/getInstance)]
		(doto calendar-obj 
			(.set Calendar/AM_PM Calendar/AM)
			(.set Calendar/HOUR 0))
		(.getTime calendar-obj)))

; clojure中如何实现 java的interface 或者 实现class的继承