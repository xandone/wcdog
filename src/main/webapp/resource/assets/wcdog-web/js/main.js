$(function () {
    var jokeList = $('#joke-list');

    var banner = $('#banner');
    var bannerBox = $('#banner-box');
    var bannerImgs;
    var boxPoints;
    var currentIndex = 0;


    initData()

    // initEvent();

    function initData() {
        getjokeList();
        getBannerImgList();
    }

    function initEvent(result) {
        setEvent();
        setReply(result);
    }


    function getjokeList(page, count) {
        $.ajax({
            url: '/wcdog/joke/jokelist',
            type: 'GET',
            dataType: 'json',
            data: {
                page: 0,
                row: 10
            },
            success: function (result) {
                createJokeList(result);
            }
        });
    }

    function getBannerImgList(page, count) {
        $.ajax({
            url: '/wcdog/banner/list',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                createBanner(result.data);
            }
        });
    }

    function createJokeList(result) {
        let jokeItemParent = $('<ul></ul>');
        let data = result.data;
        jokeList.html(jokeItemParent);

        for (var i = 0; i < data.length; i++) {
            let jokeItem = $('<li class="joke-item-li"></li>');
            let temp = '<div class="joke-item">' +
                '<a class="joke-title" href="javascript:;">' + data[i].title + '</a>' +
                '<div class="joke-author"> ' +
                '<div class="joke-author-icon-root">' +
                '<img class="joke-author-icon" src=' + data[i].jokeUserIcon + ' alt="">' +
                '<div class="joke-author-dialog">' +
                '<img src="imgs/headicon.jpg" alt="">' +
                '<span class="author-dialog-name">狗蛋</span>' +
                '<div class="joke-dialog-line"></div>' +
                '<span class="author-dialog-say">2019年的第一场雪~</span>' +
                '</div>' +
                '</div>' +
                '<span>' + data[i].jokeUserNick + '</span>' +
                '</div>' +
                '<div class="joke-content">' +
                '<span>' + data[i].content + '</span>' +
                '</div>' +
                '<span class="helpful"><img src="imgs/zan.png" alt=""><span>' + data[i].articleLikeCount + '</span></span>' +
                '<span class="helpful_comment_icon helpful"><img src="imgs/comment_icon.png" alt=""><span class="helpful_comment_icon_text">' + data[i].articleCommentCount + '</span></span>' +
                '<span class="joke-date">' + data[i].postTime + '</span>' +
                '<div  class="reply_root">' +
                '<div class="joke_comment_root">' +
                '<div class="joke_comment_ul_root">' +
                '<div class="joke_comment_first_div"><span class="joke_comment_first"></span></div>' +
                '<div>' +
                '<ul class="joke_comment_ul"></ul>' +
                '</div>' +
                '</div>' +
                '<ul class="comment_page pagination"></ul>' +
                '</div>' +
                '<div class="reply_joke" contenteditable="true"></div>' +
                '<div class="reply_btn_root"><button class="reply_joke_btn">回复</button></div>' +
                '</div>' +
                '</div>';

            jokeItem.html(temp);
            jokeItemParent.append(jokeItem);

        }
        initEvent(result.data);

    }

    function createBanner(result) {
        for (let i = 0; i < result.length; i++) {
            let imgTemp = '<a href="javascript:;" >' +
                '<img src=' + result[i].imgUrl + ' alt="">' +
                '</a>';
            banner.append(imgTemp);

            let point = '<li class="banner-box-bg"></li>';
            bannerBox.append(point);
        }

        bannerImgs = $('#banner a');
        boxPoints = $('#banner-box li');

        for (let i = 0; i < bannerImgs.length; i++) {
            $(bannerImgs[i]).removeClass();
            $(bannerImgs[i]).addClass('hidden');

            $(boxPoints[i]).removeClass('banner-box-select');
            $(boxPoints[i]).addClass('banner-box-bg');
        }

        $(bannerImgs[0]).addClass('show-banner-img');
        $(boxPoints[0]).addClass('banner-box-select');

        startLoop(bannerImgs.length);

    }


    function startLoop(len) {
        var timer = setInterval(function () {
            currentIndex++;
            if (currentIndex >= len) {
                currentIndex = 0;
            }

            for (var i = 0; i < bannerImgs.length; i++) {
                $(bannerImgs[i]).removeClass();
                $(bannerImgs[i]).addClass('hidden');

                $(boxPoints[i]).removeClass('banner-box-select');
                $(boxPoints[i]).addClass('banner-box-bg');
            }

            $(bannerImgs[currentIndex]).addClass('show-banner-img');
            $(boxPoints[currentIndex]).addClass('banner-box-select');

        }, 2000);
    }


    function setEvent() {
        $('.joke-author-icon').mouseenter(function (event) {
            /* Act on the event */
            $(this).next().show('300', function () {

            });
        }).mouseleave(function (event) {
            /* Act on the event */
            $(this).next().hide('300', function () {

            });
        });

    }

    /**
     * 回复
     * @param result
     */
    function setReply(result) {
        $('.helpful_comment_icon').on('click', function () {
            $(this).parent().find('.reply_root').toggle(200, function () {
                if (!$(this).parent().find('.reply_root').is(":hidden")) {
                    let $rootLi = $(this).parents('.joke-item-li');
                    let jokeId = result[$rootLi.index()].jokeId;
                    initComments($(this), jokeId);
                }
            });
        });

        $('.reply_joke_btn').on('click', function () {
            var $rootLi = $(this).parents('.joke-item-li');
            var jokeId = result[$rootLi.index()].jokeId;
            var userId = '1';
            var details = $(this).parent().prev().text();
            if (isEmpty(details)) {
                alert('请输入内容');
                return;
            }
            $.ajax({
                url: '/wcdog/joke/comment/add',
                type: 'GET',
                dataType: 'json',
                data: 'jokeId=' + jokeId + '&userId=' + userId + '&details=' + details,
                success: function (result) {
                    let $myReplay = $(this).parent().prev();
                    let $comment = $(this).parents('.joke-item').find('.helpful_comment_icon_text');
                    let $replyRoot = $(this).parents('.reply_root');
                    let $commentCount=$replyRoot.find('.joke_comment_first');
                    let count = Number($comment.text()) + 1;
                    let replayStr = $myReplay.text();
                    $comment.text(count);
                    $commentCount.text(count+"条评论");
                    $myReplay.text("");

                    let bean = {
                        "commentId": "154873337569716",
                        "jokeId": "154779912420664",
                        "commentUserId": "1",
                        "commentDetails": replayStr,
                        "commentDate": 1548733376000,
                        "commentNick": "狗蛋1",
                        "commentIcon": ""
                    };
                    addOneComment($replyRoot, bean);
                }.bind(this)
            })
        });
    }

    function initComments($itemRoot, jokeId) {
        $.ajax({
            url: '/wcdog/joke/comment/list',
            type: 'get',
            data: {
                page: 1,
                row: 10,
                jokeId: jokeId
            },
            dataType: 'json',
            success: function (result) {
                initPages($itemRoot, result.total, jokeId);
                createCommentList($itemRoot, result);
            }
        })
    }

    function getComments($itemRoot, num, jokeId) {
        $.ajax({
            url: '/wcdog/joke/comment/list',
            type: 'get',
            data: {
                page: num,
                row: 10,
                jokeId: jokeId
            },
            dataType: 'json',
            success: function (result) {
                createCommentList($itemRoot, result);
            }
        })
    }

    function initPages($itemRoot, total, jokeId) {
        let totalPages = Math.ceil(total / 10);
        $itemRoot.find('.joke_comment_first').text(total + '条评论');
        if (totalPages <= 0) {
            return;
        }
        $itemRoot.find('.comment_page').jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: 1,
            onPageChange: function (num, type) {
                if (type === 'change') {
                    getComments($itemRoot, num, jokeId);
                }
            }
        });
    }

    function createCommentList($itemRoot, result) {
        let commentItemParent = $itemRoot.find('.joke_comment_ul');
        let data = result.data;
        commentItemParent.html('');

        for (let i = 0; i < data.length; i++) {
            let item = $('<li></li>');
            let temp = createCommentItem(data[i]);
            item.html(temp);
            commentItemParent.append(item);
        }
    }

    function createCommentItem(bean) {
        let temp = '<div>' +
            '<img class="joke_commentor_img" src="imgs/headicon.jpg" alt="">' +
            '<div class="joke_comment_details">' +
            '<span class="joke_commentor_name">狗蛋</span>' +
            '<span class="joke_commentor_date">2019-1-30</span>' +
            '<div>' +
            '<span>' + bean.commentDetails + '</span>' +
            '</div>' +
            '</div>' +
            '</div>';
        return temp;

    }

    function addOneComment($itemRoot, bean) {
        let commentItemParent = $itemRoot.find('.joke_comment_ul');
        let item = $('<li></li>');
        let temp = createCommentItem(bean);
        item.html(temp);
        commentItemParent.append(item);
    }

});