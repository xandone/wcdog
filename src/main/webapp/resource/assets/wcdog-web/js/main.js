$(function () {
    var jokeList = $('#joke-list');

    var banner = $('#banner');
    var bannerBox = $('#banner-box');
    var bannerImgs;
    var boxPoints;
    var currentIndex = 0;


    // initData()
    initEvent();

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
                createJokeList(result.data);
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
        var jokeItemParent = $('<ul></ul>');
        jokeList.html(jokeItemParent);

        for (var i = 0; i < result.length; i++) {
            var jokeItem = $('<li class="joke-item-li"></li>');
            var temp = '<div class="joke-item">' +
                '<a class="joke-title" href="javascript:;">' + result[i].title + '</a>' +
                '<div class="joke-author"> ' +
                '<div class="joke-author-icon-root">' +
                '<img class="joke-author-icon" src=' + result[i].jokeUserIcon + ' alt="">' +
                '<div class="joke-author-dialog">' +
                '<img src="imgs/headicon.jpg" alt="">' +
                '<span class="author-dialog-name">狗蛋</span>' +
                '<div class="joke-dialog-line"></div>' +
                '<span class="author-dialog-say">2019年的第一场雪~</span>' +
                '</div>' +
                '</div>' +
                '<span>' + result[i].jokeUserNick + '</span>' +
                '</div>' +
                '<div class="joke-content">' +
                '<span>' + result[i].content + '</span>' +
                '</div>' +
                '<span class="helpful"><img src="imgs/zan.png" alt=""><span>' + result[i].articleLikeCount + '</span></span>' +
                '<span class="helpful_comment_icon helpful"><img src="imgs/comment_icon.png" alt=""><span class="helpful_comment_icon_text">' + result[i].articleCommentCount + '</span></span>' +
                '<span class="joke-date">' + result[i].postTime + '</span>' +
                '<div  class="reply_root"><div class="reply_joke" contenteditable="true"></div>' +
                '<div class="reply_btn_root"><button class="reply_joke_btn">回复</button></div></div>' +
                '</div>';

            jokeItem.html(temp);
            jokeItemParent.append(jokeItem);

        }
        initEvent(result);

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

    function setReply(result) {
        $('.helpful_comment_icon').on('click', function () {
            $(this).parent().find('.reply_root').toggle(200);
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
                    $(this).parent().prev().text("");
                    var $comment = $(this).parents('.joke-item').find('.helpful_comment_icon_text');
                    var count = Number($comment.text()) + 1;
                    $comment.text(count);
                }.bind(this)
            })
        });
    }

});