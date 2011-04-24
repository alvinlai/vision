(ns vision.views
  (:use hiccup.core
        vision.helpers))

(def menu
  [:div {:id "menu"}
   (comment
     (if (u/user-logged-in?)
       (do
         " Logged in as "
          (u/current-user)
          (if (u/user-admin?)
           " (Admin)")
          ". "
          (link-to (u/logout-url) "Logout"))
       (link-to (u/login-url) "Login")))
   [:h3 "Navigation"]
   [:ul
    (li-link "/" "Home")
    (li-link "/thoughts" "Thoughts")
    (li-link "/todos" "Todos")
    (li-link "/waiting" "Waiting")
    (li-link "/links" "Links")
    ]
   [:h3 "Mini Projects"]
   [:ul
    (li-link "/craig/cooper" "Coopers on CraigsList")
    (li-link "/test-json" "JSON Test" true)
    ]
   [:h3 "In The Works"]
   [:ul
    (li-link "/linkshare" "Linkshare" "_blank")
    ]
   ])

(defn layout
  [body]
  (html
   [:head
    [:title "Fabagax"]
    (cssfile "static/style.css")]
   [:body
    [:div {:id "header"}
     [:div {:style "font-size:50px; font-family:'Gill Sans', 'Helvetica'"}
      (comment
        [:img {:src "static/clojure.gif" :style "max-width: 35px; margin-top: 10px;"}]
        )
      (link-to "/" "Fabagax")]
     [:p "Learn web development using Clojure, Leiningen, Compojure, Emacs and Swank."]]
    [:div {:id "content"} body]
    menu
    [:div {:class "clear"}]
    [:div {:id "footer"}
     [:p
      "Powered by "
      (link-to "http://clojure.org" "Clojure")
      " and "
      (link-to "https://github.com/weavejester/compojure" "Compojure")
      ", hosted on "
      (link-to "http://dotcloud.com" "DotCloud")
      ". All rights reserved "
      (link-to "http://alvinlai.com" "Alvin Lai")
      " 2011."
      ]
     ]]
   (google-analytics "UA-22731879-3")))

(defn todo
  [done content]
  (if done
    [:li {:class "strike"} content]
    [:li content] ))

(defn thoughts
  []
  (layout
   (html
    [:h3 "Thoughts"]
    [:ul
     [:li "DotCloud doesn't seem very stable, maybe it's because of the EC2 outage, often getting timeouts pushing."]
     [:li "Moved to Fabagax to DotCloud hosting instead, encountered problems with hosting static GIFs but not CSS, sent support email, waiting reply"]
     [:li "I decided against hosting on Google App Engine with appengine-magic for a few reasons:"
      [:ul
       [:li "Warm up time"]
       [:li "Google's hasn't been consistent with external vendor support"]
       [:li "lein ring server reloads as I make file changes, very fast, don't have to C-c C-l to reload core.clj"]
       [:li "Having to uncomment (ae/serve) before deploying is annoying"]
       ]]
     [:li "DotCloud.com looks like a nice place to host my Clojure webapps, but it's still in Beta, relies on EC2, sigh maybe I'll be better off learning how to host my own Jetty based servers instead on a VPS myself"]
     [:li "Beginning to enjoy web development in Clojure even though it's just a few simple things now"]
     [:li "paredit is a must have while doing Lisp programming, it takes care of matching parentheses, with this the argument about scary looking parentheses in Lisp programming just melts away."]
     [:li "The warm up time while Hosting on Google App Engine takes around 15 seconds, a little annoying and I only get 6.5Hrs of CPU time daily on the free tier. Heroku is still faster in this regard."]])))

(defn waiting
  []
  (layout
   (html
    [:h3 "Waiting"]
    [:ol
     (todo false "DotCloud to reply about static GIFs not loading but CSS doing okay.")]
    )))

(defn questions
  []
  (layout
   (html
    [:h3 "Questions"]
    [:ul
     [:li "Should I continue to use Hiccup instead of Enlive?"]
     [:li "Can I use Hiccup to generate the templates for Enlive?"]])))

(defn todos
  []
  (layout
   (html
    [:h3 "Todos"]
    [:ol
     (todo true "Get static site up")
     (todo true "Style site")
     (todo true "Craigslist parser for Cooper")
     (todo false "Get Dotcloud to serve static files nicely")])))







(defn links
  []
  (layout
   (html
    [:h2 "Links"]
    [:h3 "Learning Clojure"]
    [:ul
     (li-link "http://alexott.net/en/clojure/ClojureLein.html" "Using Leiningen to build Clojure Core" true)
     (li-link "http://mark.reid.name/sap/setting-up-clojure.html" "Setup Clojure for Mac OS X" true)
     (li-link "http://www.bestinclass.dk/index.clj/2009/10/functional-social-webscraping.html" "Scraping + Mac Desktop widgets" true)
     (li-link "http://www.clojure-toolbox.com/" "Clojure Toolbox" true)
     (li-link "https://www.4clojure.com" "Koan-style Clojure quiz" true)
     (li-link "http://disclojure.org/projects/" "Good Clojure projects listing" true)
     (li-link
      "https://github.com/stuarthalloway/clojure-presentations/blob/master/ClojureForRubyists-1up.pdf"
      "Clojure for Rubyists PDF" true)
     (li-link "http://learn-clojure.com/clojure_videos.html" "Clojure Videos and Tutorials" true)
     (li-link "http://vimeo.com/8356990" "Compojure Emacs Swank Screencast"  true)
     (li-link "http://dev.clojure.org/display/doc/Getting+Started+with+Emacs" "Get started with Emacs for Clojure"  true)
     (li-link "http://blog.markwatson.com/2010/04/my-clojure-mongodb-ring-and-compojure.html" "Compojure with Mongo"  true)
     ]
    [:h4 "Hackers with Attitude: Creators of The Deadline Clojure GAE App"]
    [:ul
     (li-link "http://www.hackers-with-attitude.com/2009/08/intertactive-programming-with-clovjure.html" "Interactive programming with Clojure, Compojure, Google App Engine and Emacs"  true)
     (li-link "http://www.hackers-with-attitude.com/2010/04/clojure-google-app-engine-setup-update.html" "GAE Setup"  true)
     (li-link "http://www.hackers-with-attitude.com/2010/05/dsl-to-access-google-app-engine.html" "DSL for GAE"  true)
     (li-link "http://www.slideshare.net/smartrevolution/how-a-clojure-pet-project-turned-into-a-fullblown-cloudcomputing-webapp" "Pet Clojure project to full blown web app" true)
     (li-link "http://www.slideshare.net/smartrevolution/writing-html5-apps-with-google-app-engine-google-closure-library-and-clojure?from=ss_embed" "Google Closure with Clojure" true)
     (li-link "https://github.com/smartrevolution/clj-gae-datastore" "GAE Datastore" true)

     ]
    [:h3 "Useful Libraries"]
    [:ul
     (li-link "https://github.com/gcv/appengine-magic" "App Engine Magic" true)
     (li-link "https://github.com/cgrand/enlive/" "Enlive" true)
     (li-link "https://github.com/swannodette/enlive-tutorial" "Enlive Tutorial" true)
     (li-link "https://github.com/danlarkin/clojure-json/" "Clojure JSON" true)
     (li-link "https://github.com/mmcgrana/clj-http" "HTTP Client" true)
     ]
    [:h3 "Interesting Clojure Projects"]
    [:ul
     (li-link "https://github.com/technomancy/mire" "Clojure Multi User Dungeon (MUD)" "_blank" true)
     ]
    [:h3 "Paid Education"]
    [:ul
     (li-link "http://peepcode.com/products/functional-programming-with-clojure" "Peepcode Clojure Screencast by Phil Hagelberg (Creator of Leiningen" "_blank" true)
     ]
    )))

(defn tips
  []
  (layout
   (html
    [:h2 "Tips"]
    [:h3 "Emacs shortcuts"]
    [:ul
     [:li "C-c C-z: bring up REPL, C-x C-0 to close it"]])))

(defn page-not-found-404
  []
  (html
   [:h1 "Sorry!"]
   [:p "The page you're trying to access is not available."]))
