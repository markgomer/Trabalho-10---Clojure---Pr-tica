;; Marco Aurélio Silva de Souza Júnior


;; 1. Na aula disponível em:
;; https://replit.com/@frankalcantara/ClojureAula2?v=1 foram destacadas 
;; diversas funções (expressões), como funções de primeira ordem, 
;; disponíveis em Clojure. Sua primeira tarefa será descrever cada uma 
;; destas funções e apresentar dois exemplos de uso de cada uma delas. 
;; Lembre-se os exemplos precisam ser utilizados de forma que o resutado da 
;; função possa ser visto no terminal.)

;; assoc
(defn assocExemplo "insere item no final de um mapa"
    [mapa novaChave novoValor]
    (assoc mapa novaChave novoValor)  )

(println "Entrada: \n(assocExemplo {:x 1 :y 2} :z 3); \nSaída:")
(println (assocExemplo {:x 1 :y 2} :z 3))

(println "Entrada: \n(assocExemplo {:leite 16.5 :pao 2} :queijo 20.99); \nSaída:")
(println (assocExemplo {:leite 16.5 :pao 2} :queijo 20.99))

;; dissoc
(defn dissocExemplo "remove item que tem a correspondente chave de um mapa"
    [mapa chave]
    (dissoc mapa chave)  )

(println "Entrada: \n(dissocExemplo {:leite 16.5 :pao 2 :queijo 20.99} :leite); \nSaída:")
(println (dissocExemplo {:leite 16.5 :pao 2 :queijo 20.99} :leite))

(println "Entrada: \n(dissocExemplo {:x 2 :y 2 :z 3} :y); \nSaída:")
(println (dissocExemplo {:x 2 :y 2 :z 3} :y))


;; range: retorna sequencia de 0 a infinito
(println "RANGE")
(println range)
;; range n: retorna sequencia de 0 a n
(println (range 5))
;; range begin end: retorna sequencia de begin a end
(println (range 5 10))
;; range begin end step: retorna sequencia de begin a end com razão definida em step
(println (range 2 16 2))

;; map: aplica uma função a uma coleção de itens
(println (map * [1 2 3] [3 2 1])) ; multiplica os elementos da mesma posição de cada lista
(println (map + [1 2 3] [3 2 1])) ; soma os elementos correspondentes de cada lista
(println (map inc [3 2 1])) ; incrementa em 1 os elementos
(println (map - [3 2 1])) ; inverte sinal dos elementos

;; filter
(println (filter odd? [1 2 3])) ; retorna sequencia com ímpares
(println (filter some? '(1 nil [] :a nil))) ; remove os nil's

;; (into to from)
;; aplica conj da coleção 'from' para a coleção 'to'
(println (into [1 2 3] '(3 2 1)))
(println (into () '(1 2 3)))


;; 2. Utilizando a linguagem Clojure, crie uma função 
;; chamada ehPrimo que receba um inteiro e devolva 
;; True caso este inteiro seja verdadeiro e False caso 
;; contrário. 
(def allInts
    (cons 1
        (lazy-seq (map inc allInts))))

(defn divisores
    [n]
    (->> allInts
        (take-while 
            (fn [i] 
                (<= i n)))
        (filter 
            (fn [i] (zero? (rem n i))))))

(defn ehPrimo?
    [n]
    (= [1 n] (divisores n)))

(println "É primo? " (ehPrimo? 2))
(println "É primo? " (ehPrimo? 4))
(println "É primo? " (ehPrimo? 43))
(println "É primo? " (ehPrimo? 44))

;; 3. Utilizando a linguagem Clojure, crie uma função 
;; chamada fatoresPrimos que receba um inteiro e devolva
;; uma lista dos seus fatores primos. Decomposição em 
;; fatores primos é uma tarefa fundamental da aritmética.
(defn fatoresPrimos [n]
    (if (<= n 1)
        []
        (if (= n 2)
            [2]
            (if (even? n)
                (cons 2 (fatoresPrimos (/ n 2)))
                (loop [i 3]
                    (if (>= i (inc (int (Math/sqrt n))))
                    [n]
                    (if (zero? (rem n i))
                        (cons i (fatoresPrimos (/ n i)))
                        (recur (inc i)))))))))

(print "Fatores primos de 50 = " )
(println (fatoresPrimos 50 ) )
(print "Fatores primos de 100 = " )
(println (fatoresPrimos 100 ) )
(print "Fatores primos de 200 = " )
(println (fatoresPrimos 200 ) )


;; 4. Utilizando a linguagem Clojure, crie 
;; uma função chamada todosPrimos que receba dois 
;; valores inteiros e devolve todos os números 
;; primos que existam entre estes dois valores.
(defn todosPrimos [n m]
    (if (<= n 1)
        []
        (if (= n 2)
            (if (<= m 2)
                [2]
                (cons 2 (todosPrimos 3 m)))
            (if (even? n)
                (todosPrimos (inc n) m)
                (loop [i n]
                    (if (>= i m)
                    []
                    (if (ehPrimo? i)
                        (cons i (todosPrimos (inc i) m))
                        (recur (inc i)))))))))

(print "Primos entre 2 e 50: ")
(println (todosPrimos 2 50 )) 
(print "Primos entre 50 e 100: ")
(println (todosPrimos 50 100)) 
