(ns customcomponents.core
  (:require [reagent.core :as r]))


(defn Note [props child]
  [:div props
   "NOTICE! "
   child])

(def ClassNote
  (r/reactify-component
   (fn [props child]
     [:div "ADAPTED NOTICE! " child])))

(r/set-custom-components! {:Note #'Note
                           :ClassNote ClassNote})

(defn custom-components-example []
  [:<>
   [:Note#note1 "Here is a simple note"]
   [:Note#note2>span.color-blue "Here is a blue note"]
   [:div#note3-wrapper.bg-blue>Note#note3 "Here is a blue background note"]
   [:Note#nested-note>Note#child-note "Here is a nested note"]
   [:ClassNote#class-note.color-blue>Note "Here is an adapted react class"]])

(defn ^:export run []
  (r/render [custom-components-example]
            (js/document.getElementById "app")))
