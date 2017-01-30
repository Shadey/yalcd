(ns yalcd.yalcd
  (:require [clojure.string :as str])
  (:require [clojure.data.json :as json])
  (:require [clojure.java.io :as io])
  (:gen-class))
(defn write-file
    "Write to file from a url"
    [filepath source]
    (io/make-parents filepath)
    (with-open [input (io/input-stream source)
                output (io/output-stream filepath)]
            (io/copy input output)))
(defn download
  "Downloads images from thread"
  [thread]
  (let [board (nth (str/split thread #"/") 3)
        no (last (str/split thread #"/"))
        media (format "https://lainchan.org/%s/src/" board)
        thread-json (json/read-str (slurp (format "%s%s" (str/replace thread #".html" "") ".json")))]
        (doseq [post (get thread-json "posts")]
               (if (contains? post "filename")
                   (if (contains? post "extra_files")
                       (doseq [extra-post (get post "extra_files")]
                              (let [filename (str (get extra-post "tim") (get extra-post "ext"))
                                    url (str media filename)
                                    abs-path (format "%s_%s/%s" board no filename)]
                               (write-file abs-path url)))
                           (let [filename (str (get post "tim") (get post "ext"))
                                 url (str media filename)
                                 abs-path (format "%s_%s/%s" board no filename)]
                             (write-file abs-path url)))))))
                       
(defn -main
  "Starts the download"
  [& args]
  (download (first args)))
