package com.ly.luoyan.fasiondemo.model;

import java.util.List;

/**
 * Created by luoyan on 2016/11/11.
 */

public class JianKeSearchModel {

    private int paginated;

    public int getTotal() {
        return paginated;
    }

    public void setTotal(int paginated) {
        this.paginated = paginated;
    }

    /**
     * succeed : 1
     */

    private StatusBean status;
    /**
     * id : 748
     * user_id : 2
     * content : 来了
     * title : null
     * likes : 0
     * comment_num : 0
     * crtime : 1478663203
     * nickname : Desire
     * avatar : http://117.71.56.131:8091/Uploads/Pictures/Avatar/avatar.jpeg
     * is_follow : 1
     * is_like : 0
     * album : [{"id":"730","url":"http://117.71.56.131:8091/Uploads/Pictures/2016-11-09/58229c2386eb9.png","width":null,"height":null,"pic_desc":null,"crtime":"1478663203","tag":[{"p_x":"0.01","p_y":"0.01","words":"狐狸"}]},{"id":"731","url":"http://117.71.56.131:8091/Uploads/Pictures/2016-11-09/58229c2388f07.png","width":null,"height":null,"pic_desc":null,"crtime":"1478663203","tag":[{"p_x":"0.01","p_y":"0.01","words":"狐狸"}]}]
     * time : 1天前
     */

    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class StatusBean {
        private int succeed;
        private int error_code;
        private String error_desc;

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_desc() {
            return error_desc;
        }

        public void setError_desc(String error_desc) {
            this.error_desc = error_desc;
        }

        public int getSucceed() {
            return succeed;
        }

        public void setSucceed(int succeed) {
            this.succeed = succeed;
        }
    }

    public class DataBean {
        private String id;
        private String user_id;
        private String content;
        private Object title;
        private String likes;
        private String comment_num;
        private String crtime;
        private String nickname;
        private String avatar;
        private String is_follow;
        private String is_like;
        private String time;
        /**
         * id : 730
         * url : http://117.71.56.131:8091/Uploads/Pictures/2016-11-09/58229c2386eb9.png
         * width : null
         * height : null
         * pic_desc : null
         * crtime : 1478663203
         * tag : [{"p_x":"0.01","p_y":"0.01","words":"狐狸"}]
         */

        private List<AlbumBean> album;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getCrtime() {
            return crtime;
        }

        public void setCrtime(String crtime) {
            this.crtime = crtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<AlbumBean> getAlbum() {
            return album;
        }

        public void setAlbum(List<AlbumBean> album) {
            this.album = album;
        }

        public class AlbumBean {
            private String id;
            private String url;
            private int width;
            private int height;
            private Object pic_desc;
            private String crtime;
            /**
             * p_x : 0.01
             * p_y : 0.01
             * words : 狐狸
             */

            private List<TagBean> tag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public Object getPic_desc() {
                return pic_desc;
            }

            public void setPic_desc(Object pic_desc) {
                this.pic_desc = pic_desc;
            }

            public String getCrtime() {
                return crtime;
            }

            public void setCrtime(String crtime) {
                this.crtime = crtime;
            }

            public List<TagBean> getTag() {
                return tag;
            }

            public void setTag(List<TagBean> tag) {
                this.tag = tag;
            }

            public class TagBean {
                private String p_x;
                private String p_y;
                private String words;

                public String getP_x() {
                    return p_x;
                }

                public void setP_x(String p_x) {
                    this.p_x = p_x;
                }

                public String getP_y() {
                    return p_y;
                }

                public void setP_y(String p_y) {
                    this.p_y = p_y;
                }

                public String getWords() {
                    return words;
                }

                public void setWords(String words) {
                    this.words = words;
                }
            }
        }
    }
}
