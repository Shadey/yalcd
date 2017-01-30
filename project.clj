(defproject yalcd "0.1.0-SNAPSHOT"
  :description "Downloads a lainchan thread"
  :url "http://github.com/Shadey/yalcd"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot yalcd.yalcd
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
