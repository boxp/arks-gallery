(ns arks-gallery-server.app.resolvers)

(def users (clj->js [{:id 1 :name "marisa" :ship_num 3 :profile_img_url "dummy" :following [2] :followers [2]}
                     {:id 2 :name "alice" :ship_num 3 :profile_img_url "dummy" :following [1] :followers [1]}
                     {:id 3 :name "reimu" :ship_num 3 :profile_img_url "dummy" :following [1] :followers [1]}
                     {:id 4 :name "ru-mia" :ship_num 3 :profile_img_url "dummy" :following [1] :followers [1]}]))

(def ships (clj->js [{:num 3 :name "ソーン"}]))

(def images (clj->js [{:id 1 :name "hoge" :width 1200 :height 1920 :placeholder_color "#fff" :url "https://firebasestorage.googleapis.com/v0/b/boxp-tk.appspot.com/o/arks-gallery-dev%2Fpso20180108_235703_011.jpg?alt=media&token=993d2f23-acd1-4706-841b-1a0408e9ff6f" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}
                      {:id 2 :name "hoge" :width 1920 :height 1200 :placeholder_color "#fff" :url "https://firebasestorage.googleapis.com/v0/b/boxp-tk.appspot.com/o/arks-gallery-dev%2Fpso20180207_022451_000.jpg?alt=media&token=74069b93-50d1-4fa8-8991-927786e3b55c" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}
                      {:id 3 :name "hoge" :width 2560 :height 1440 :placeholder_color "#fff" :url "https://firebasestorage.googleapis.com/v0/b/boxp-tk.appspot.com/o/arks-gallery-dev%2Fpso20180304_135339_006.jpg?alt=media&token=5affab01-59c1-449f-b483-8fe6b2798c86" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}
                      {:id 4 :name "hoge" :width 1920 :height 1080 :placeholder_color "#fff" :url "https://dummyimage.com/1920x1080/000/fff&text=dummyimage" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}
                      {:id 5 :name "hoge" :width 1920 :height 1080 :placeholder_color "#fff" :url "https://dummyimage.com/1920x1080/000/fff&text=dummyimage" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}
                      {:id 6 :name "hoge" :width 1920 :height 1080 :placeholder_color "#fff" :url "https://dummyimage.com/1920x1080/000/fff&text=dummyimage" :tweet_count 0 :comment_ids [1] :tag_ids [1] :like_ids [1] :owner_id 1}]))

(def comments (clj->js [{:id 1 :text "hogehoge" :reply_to_id nil :owner_id 1}]))

(def tags (clj->js [{:id 1 :name "hoge" :owner_id 1}]))

(def comment_likes (clj->js [{:from_id 1 :to_id 1}]))
(def image_likes (clj->js [{:from_id 1 :to_id 1}]))

(def follows (clj->js [{:from_id 1 :to_id 2}
                       {:from_id 2 :to_id 1}]))

(def resolve-functions
  (clj->js
    {:Query
     {:images (fn []
                (clj->js images))
      :users (fn []
               (clj->js users))}
     :User
     {:ship (fn [user]
              (->> ships
                   (filter #(= (.-num %) (.-ship_num user)))
                   first))
      :following (fn [user]
                   (->> follows
                        (filter #(and ((-> user .-following set) (.-to_id %))))))
      :followers (fn [user]
                   (->> follows
                        (filter #((-> user .-following set) (.-to_id %)))))
      }
     :Image
     {:comments (fn [image]
                  (->> comments
                       (filter #((-> image .-comment_ids set) (.-id %)))))
      :tags (fn [image]
              (->> tags
                   (filter #((-> image .-tag_ids set) (.-id %)))))
      :likes (fn [image]
               (->> image_likes
                    (filter #((-> image .-like_ids set) (.-to_id %)))))
      :owner (fn [image]
               (->> users
                    (filter #(= (-> image .-owner_id) (.-id %)))
                    first))}
     :Comment {:reply_to (fn [comment]
                           (->> comments
                                (filter #(= (-> comment .-reply_to_id) (.-id %)))
                                first))
               :likes (fn [comment]
                        (->> comment_likes
                             (filter #((-> comment .-likes set) (.-to_id %)))))
               :owner (fn [comment]
                        (->> users
                             (filter #(= (-> comment .-owner_id) (.-id %)))
                             first))}
     :Tag {:owner (fn [tag]
                    (->> users
                         (filter #(= (.-owner_id tag) (.-id %)))
                         first))}
     :Like {:from (fn [like]
                    (->> users
                         (filter #(= (.-from_id like) (.-id %)))
                         first))
            :to (fn [like]
                  (->> users
                       (filter #(= (.-to_id like) (.-id %)))
                       first))}
     :Follow {:from (fn [follow]
                      (->> users
                           (filter #(= (.-from_id follow) (.-id %)))
                           first))
              :to (fn [follow]
                    (->> users
                         (filter #(= (.-to_id follow) (.-id %)))
                         first))}}))
