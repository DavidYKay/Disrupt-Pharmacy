(ns pharmacy.components.questions-box
  (:require
   [re-frame.core :as re-frame :refer [dispatch subscribe]]
   [reagent.core :as reagent])
  (:require-macros [reagent.ratom :refer [reaction]]))

(def questions
  [{:question "What is your favorite color?"
    :type :multiple-choice
    :choices ["Red"
              "Blue"
              "Green"
              "Black"]}
   {:question "Are you a smoker?"
    :type :boolean}
   {:question "Are you taking BP Meds?"
    :type :boolean}
   {:question "Do you have a history of early cardiovascular disease in your family?"
    :type :boolean}
   ])

(defn questions-box [x]
  (let [pos (reagent/atom 0)
        on-yes #(swap! pos inc)
        on-no on-yes
        active-qs (reaction (->> questions
                                 (drop @pos)
                                 (take 2)))]
    (fn []
      [:section.section
       [:div.container.box
        [:h1.title "Questions box"]
        [:h2.subtitle (str "Pos:" @pos)]
        [:h2.subtitle (str "active qs:" @active-qs)]
        (let [a (first @active-qs)]
          (when a
            [:div.question.a
             [:div (:question a)]
             [:a.button
              {:on-click on-yes}
              "Yes"]
             [:a.button {:on-click on-no} "No"]]))
        (let [b (second @active-qs)]
          (when b
            [:div.question.b
             [:div (:question b)]
             [:a.button {:on-click on-yes} "Yes"]
             [:a.button {:on-click on-no} "No"]
             ]))]])))

