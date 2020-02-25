(ns customcomponents.core
  (:require [reagent.core :as r]))


(defn Note [props & children]
  (into [:div props "NOTICE! "] children))

(def ClassNote
  (r/reactify-component
   (fn [props & children]
     (into [:div "ADAPTED NOTICE! "] children))))

(r/set-custom-components! {:Note #'Note
                           :ClassNote ClassNote})

(defn custom-components-example []
  [:<>
   [:Note#note1 "Here is a simple note"]
   [:Note#note2>span.color-blue "Here is a blue note"]
   [:div#note3-wrapper.bg-blue>Note#note3
    {:style {:color "red"}}
    "Here is a blue background note"]
   [:Note#nested-note>Note#child-note
    "Here is a nested note"
    [:div "with multiple children"]]
   [:ClassNote#class-note.color-blue>Note
    {:style {:background-color "red"}}
    "Here is an adapted react class"
    [:div "with multiple children"]
    [:div "and props"]]])

(defn ^:export run []
  (r/render [custom-components-example]
            (js/document.getElementById "app")))
