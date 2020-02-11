(ns core)

(def klipse-repo {:name  "klipse"
                  :stars 23451
                  :forks 1254})

(def titeuf-repo {:name  "titeuf"
                  :stars 1432
                  :forks 52})

(def spirou-repo {:name "spirou"
                  :stars 455
                  :forks 9})

(def viebel-user {:username      "viebel"
                  :name          "Yehonathan Sharvit"
                  :email         "ys@me.com"
                  :organizations ["ibm" "google"]
                  :repositories  {"klipse" klipse-repo
                                  "titeuf" titeuf-repo
                                  "spirou" spirou-repo}})

(def zepad-user {:username      "zepad"
                 :name          "Zachary Tanner"
                 :email         "zt@me.com"
                 :organizations ["microsoft"]
                 :repositories  {"tintin" {:name "tintin"
                                           :stars 552
                                           :forks 43}
                                 "donjon" {:name "donjon"
                                           :stars 987
                                           :forks 76}}})

(def github-db {"zepad" zepad-user
                "viebel" viebel-user})

;; map, string, string -> Number
(defn num-of-stars [db user repo]
  ;; queries how many start has a repositories
  (get-in db [user :repositories repo :stars]))

;; map , string, string  -> Number
(defn num-of-repo [db user]
  ;; queries how many repositories user have.
  (count (get-in db [user :repositories])))

;; map, string -> Number
(defn num-of-orgs [db user]
  ;; queries how many organization user have
  (count (get-in db [ user :organizations])))

;; map, string, string -> new map
(defn change-email-of-user! [db user email]
  ;; changing the email address of a user
  (assoc-in db [user :email] email))

;; map, string, string -> new map
(defn add-one-star! [db user repo]
  ;; increment one star in repository
  (update-in db [user :repositories repo :stars] inc))

;; map, string, string/vector -> new map
;; In order to add an organization to a user we use update-in
;; and an anonymous function that appends
;; an element to the vectors of organizations:
(defn add-organization! [db user org]
  (update-in db [user :organizations]
             (fn [organizations]
               (conj organizations org))))


(num-of-stars github-db "zepad" "tintin")
(num-of-repo github-db "zepad")
(num-of-orgs github-db "zepad")
(change-email-of-user! github-db "viebel" "sharon@elminda.com")
(add-one-star! github-db "zepad" "tintin")
(add-organization! github-db "viebel" "elminda")







