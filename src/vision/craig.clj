(ns vision.craig
  (:import java.net.URL)
  (:require [clojure.contrib.string :as str-utils :only (substring? lower-case)])
  (:use hiccup.core
        vision.views
        vision.helpers))

(use 'net.cgrand.enlive-html)

; filter function posts only with pics
(defn got-pic?
  [l]
  (= "p" (:class (:attrs (first (drop 7 (:content l)))))))

(defn search-url
  [query-str]
  (str "http://sfbay.craigslist.org/search/cto?query=" query-str "&srchType=T&minAsk=&maxAsk="))

; transform into nice hashes
(defn process-post
  [post]
  (let [p (first (drop 3 (:content post)))]
    {:title (first (:content p))
     :link (:href (:attrs p))
     :price (.trim (first (drop 4 (:content post))))}))

(defn get-raw-posts
  [query-str]
  (-> (search-url query-str) java.net.URL. html-resource
                  (select [:.row])))

(defn get-posts-with-pics
  [query-str]
  (let [links (get-raw-posts query-str)]
    (map process-post
                     (filter got-pic? links))))

(defn html-format-post
  [post]
  (html
   [:li
    [:a {:href (:link post) :target "_blank"} (:title post)]
    (:price post)]))

(defn html-craig-query
  [query-str]
  (let [rand-query-str (str "/craig/" (rand-nth ["mustang" "bmw" "mercedes" "honda" "volkswagen"]))]
    (layout
     (html
      [:h2 (str "Simple Craiglist Parser for " query-str) ]
      [:p "Protip: Try changing the url to "
       (link-to rand-query-str rand-query-str)
       " instead."]
      (map html-format-post (get-posts-with-pics query-str))))))

;;;;; for repl debugging

; redundant, after search query is called in url directly
(defn title-filter
  [title]
  #(str-utils/substring? title (str-utils/lower-case (:title %))))

(defn print-post
  [post]
  (let [p post]
    (println (str "- " (:title p) " :: " (:link p)))))

(defn pres
  [filter-str]
  (map print-post (get-posts-with-pics filter-str))
  ;(map print-post (filter (title-filter filter-str)
  ;(get-posts-with-pics)))
)

; (map print-post carpics)
; (print-post (first carpics))
; (count carpics)
