(ns vision.views.homepage
  (:use hiccup.core
        vision.helpers
        vision.views))

(defn homepage-index
  []
  (layout
   (html
    [:h2 "What's this?"]
    [:ul
     [:li
      "This is a webpage powered by "
      (link-to "http://clojure.org" "Clojure")
      " hosted on "
      [:a {:href "http://appengine.google.com" :style "text-decoration: line-through;"} "Google App Engine"]
      " "
      (link-to "http://dotcloud.com" "DotCloud" true)
      " run by "
      (link-to "http://alvinlai.com" "Alvin Lai")
      "."]
     [:li "I made this for several purposes:"
      [:ul
       [:li "Learn and document my journey in learning Clojure"]
       [:li "Practise deploying it online for real world practise"]
       [:li "Holding place for mini projects I'd develop using Clojure"]]]
     ]
    ;;
    [:h3 "Changelog"]
    [:ul
     [:li "24 Apr 2011 : Moved Fabagax from Google App Engine to DotCloud, re-organized into separate pages"]
     [:li "23 Apr 2011 : Simple Craig's List parser for Mini Coopers using Enlive"]
     [:li "22 Apr 2011 : Get static site up on Google App Engine with login using "
      (link-to "https://github.com/weavejester/hiccup" "Hiccup")
      " for templating"]]
    ;;
    [:h3 "DotCloud Deploy Notes"]
    [:p
     "$ lein ring uberwar && dotcloud push fabagax.www public"]
    [:p "where public/ contains the war file compiled by lein ring uberwar"]
    [:h3 "GAE Deploy Notes"]
    [:ol
     [:li ";comment (ae/serve your-app) right before you compile to run on Google App Engine."]
     [:li "$ lein appengine-prepare && appcfg.sh update war/"]
     ])))


