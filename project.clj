(defproject vision "1.0.1-SNAPSHOT"
  :description "Clojure Website on DotCloud"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.6.2"]
                 [hiccup "0.3.4"]
                 [enlive "1.0.0"]
                 [org.danlarkin/clojure-json "1.2-SNAPSHOT"]
                 ]
  :dev-dependencies [[lein-ring "0.4.0"]
                     [swank-clojure "1.2.0"]]
  :uberjar-name "public/root.war"
  :ring {:handler vision.core/app})
