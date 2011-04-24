(ns vision.helpers
  (:use hiccup.core))

(defn google-analytics
  [identifier]
  (str
   "<script type='text/javascript'>
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', '"
   identifier
   "']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>"))

(defn cssfile
  [filename]
  (html
   [:link {:rel "stylesheet"
           :type "text/css"
           :href (str "/" filename)}]))

(defn link-to
  [link, desc & bblank]
  (let [params {:href link}]
    (if bblank
      (html [:a (conj {:target "_blank"} params) desc])
      (html [:a params desc]))))
  
(defn li-link
  [link title & bblank]
  (html
   [:li
    (if bblank
      (link-to link title bblank)
      (link-to link title))]))
