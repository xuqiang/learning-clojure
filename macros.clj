; macros
; clojure中的宏 和 函数很接近。不同之处是 函数返回value macro返回表达式
; macros和c语言中的宏定义很类似，编译器会讲宏定义展开

; macroexpand macroexpand-1两个函数能够展开宏定义 适合debug
(defmacro unless [test then]
	`(if (not ~test)
		~then))

; 语句块
(defmacro unless [test & exprs]
	`(if (not ~test)
		(do ~@exprs)))

; 宏定义生成变量名称 定义变量now#
(defmacro def-logged-in [fn-name args & body]
	`(defn ~fn-name ~args
		(let [now# (System/currentTimeMillis)]
			(println "[" now# "] call to " (str (var ~fn-name)) )
			~@body)))
