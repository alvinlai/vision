(ns vision.core
  (:use hiccup.core
        compojure.core
        vision.views
        vision.views.homepage
        vision.helpers
        vision.craig
        vision.linkshare
        ring.middleware.file
        )
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [org.danlarkin.json :as json])
  (:gen-class))

(defroutes app
  (GET "/" [] (homepage-index))
  
  (GET "/todos" [] (todos))
  (GET "/waiting" [] (waiting))
  (GET "/links" [] (links))
  (GET "/questions" [] (questions))
  (GET "/thoughts" [] (thoughts))
  
  (GET "/craig/:query" [query] (html-craig-query query))
  (GET "/linkshare" req (linkshare-homepage))
  (GET "/test-json" req (json/encode {:response "Success!"}))
  (route/files "/")
  (comment
    (GET "/*" {params :params}
         (or (serve-file "static" (params :*)) :next))
    (ANY "*" (page-not-found))
    )
  )

(comment
  (def app
  (handler/site vision-app-handler))
  )


