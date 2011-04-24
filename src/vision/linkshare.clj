(ns vision.linkshare
  (:use vision.helpers
        vision.views
        hiccup.core))


(defn linkshare-homepage
  []
  (layout
   (html
    [:h1 "Linkshare"]
    [:h2 "The need"]
    [:ul
     [:li "Share links of several nature with wife and friends:"
      [:ul
       [:li "Handpicked sites"]
       [:li "Craigslist for houses and cars"]]]]
    [:h2 "Benefits"]
    [:ul
     [:li "Share stuff privately with relevant groups"]
     [:li "Mark as read for individual user, so they can keep track"]])))

      

