(let (
  (if (lambda (c t f) (cond (c t) (true f))))
  (not (lambda (x) (eq x false)))
  (iscons (lambda (x) (not (isatom x))))
  (isatom atom) <isatom, iscons and isfunc should be buitin>
  (sameatom (lambda (x y) (cond (
      ((not (isatom x)) false)
      ((not (isatom y)) false)
      (true (eq x y))))))
  (isnil (lambda (x) (if (atom x) (eq nil x) false)))
  (filter (lambda (c l) (cond (
      ((isnil l) nil)
      ((c (car l)) (cons (car l) (filter c (cdr l))))
      (true (filter c (cdr l)))))))
  (map (lambda (f l) (if (isnil l) nil
     (cons (f (car l)) (map f (cdr l))))))
  (foldl (lambda (f s l) (if (isnil l) s
     (foldl f (f s (car l)) (cdr l)))))
  (foldr (lambda (f s l) (if (isnil l) s
     (f (car l) (foldr f s (cdr l))))))
  (reduce foldl)
  (repeat (lambda (x) (cons x (repeat x))))
  (and (lambda l (reduce (lambda (x y) (if x y false)) true l)))
  (or (lambda l (reduce (lambda (x y) (if x true y)) false l)))
  (xor (lambda l (reduce (lambda (x y) (if x (not y) y)) false l)))
  (newmacro ...)
  (apply ...)
  (currylambda ...)
 ) true)
