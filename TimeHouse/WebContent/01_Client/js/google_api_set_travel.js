// JavaScript Document

var directionsDisplay;
var directionsService = new google.maps.DirectionsService();

function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var styleArray = [
      {
          featureType: 'all',
          stylers: [
            { hue: '#1bbc9b' },
            { saturation: '-80' }
          ]
      }, {
          featureType: 'water',
          elementType: 'geometry.fill',
          stylers: [
          { visibility: 'on' },
          { hue: '#00ffdd' },
          { Saturation: '-50' }
          ]
      }
    ];
    var map;
    var markersArray = []; //瞄點陣列
    var myLatLng = new google.maps.LatLng(24.1607685, 120.6337923); //預設地圖位置

    var panoramaOptions = {
        panControl: false,/* 關閉羅盤* */
        panControlOptions: { position: google.maps.ControlPosition.LEFT_CENTER },/* 設定羅盤的位置* */
        zoomControl: false,/* 關閉放大縮小* */
        zoomControlOptions: { position: google.maps.ControlPosition.LEFT_CENTER },/* 設定zoom放大縮小的位置* */
        addressControlOptions: { position: google.maps.ControlPosition.LEFT_CENTER },/* 設定地址及反回地圖的位置* */
        enableCloseButton: true,/* 關閉功能打開* */
        visible: false //set to false so streetview is not triggered on the initial map load
    }; //街景圖設定

    var panorama = new google.maps.StreetViewPanorama(document.getElementById("map_canvas"), panoramaOptions); //定義街景圖於map_canvas div中顯示

    var mapOptions = {
        zoom: 16, //地圖Zoom
        center: myLatLng,
        styles: styleArray,
        scrollwheel: true, //滑鼠滾輪地圖Zoom
        draggable: true, //滑鼠拖曳地圖
        disableDoubleClickZoom: false, //關閉點擊地圖兩下zoom in
        streetView: panorama, //this is where we set the panorama object to the map
        mapTypeControlOptions: {
            mapTypeIds: [google.maps.MapTypeId.ROADMAP]
        },

        /* 設定地圖街道圖OR衛星圖選項* */
        mapTypeControl: false,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
            position: google.maps.ControlPosition.LEFT_CENTER
        },

        /* 設定Zoom Bar位置* */
        zoomControl: true,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.LARGE,
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        /* 比例尺設定* */
        scaleControl: false,
        /* 設定小黃人街景服務位置* */
        streetViewControl: true,
        streetViewControlOptions: {
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        addressControlOptions: {
            position: google.maps.ControlPosition.BOTTOM_CENTER
        },
    };

    /* 地圖Div設定* */
    var map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById('directions-panel'));

    /*google.maps.event.addDomListener(window, 'load', initialize);*/

    /* 各景點介紹的錨點圖設定* */
    var image = new google.maps.MarkerImage('images/1349748546_location_pin.png', new google.maps.Size(16, 26), new google.maps.Point(0, 0), new google.maps.Point(0, 0));
    /* 景點的錨點圖設定(飯店logo)* */
    var image2 = new google.maps.MarkerImage('images/logo_map.png', new google.maps.Size(86, 80), new google.maps.Point(0, 0), new google.maps.Point(0, 80));//調整logo位置 高度除2
    /* 點擊地圖關閉景點訊息框* */
    google.maps.event.addListener(map, 'click', function () {
        infowindow.close();
    });
    /* 各景點訊息框大小設定* */
    var infowindow = new google.maps.InfoWindow(
    { size: new google.maps.Size(300, 700) });

    $(".CheckCount").click(function () {
        var CheckVal = $(this).val();
        var checkSplit = CheckVal.split(",");
        var Lat = checkSplit[0];
        var Lng = checkSplit[1];
        var contentString = $(this).attr("google_title");
        if ($(this).prop("checked")) {
            createMarker(Lat, Lng, contentString);
        } else {
            /* 在要刪除指定座標前，先把勾選的座標帶入MapAPI轉換後的座標(帶入的座標跟帶出的座標有差異)* */
            var myLatLng = new google.maps.LatLng(Lat, Lng);
            DeleteMarker(myLatLng);
        }
    });
    /*在地圖上建立錨點座標Function*/
    function createMarker(lat, Lng, contentString) {
        var myLatLng = new google.maps.LatLng(lat, Lng);
        var marker = new google.maps.Marker({
            position: myLatLng,
            center: myLatLng,
            map: map,
            icon: image,
            clickable: true,
        });
        markersArray.push(marker);
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.setContent(contentString);
            infowindow.open(map, marker);
            map.panTo(marker.position);
        });
        //勾選後立即顯示景點資訊框
        google.maps.event.trigger(marker, "click");
    }

    function createMarker2(lat, Lng, contentString) {
        var myLatLng = new google.maps.LatLng(lat, Lng);
        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            icon: image2,
            clickable: true
        });
        markersArray.push(marker);
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.setContent(contentString);
            /*infowindow.open(map, marker);*/
        });
        //勾選後立即顯示其訊息
        google.maps.event.trigger(marker, "click");
    }

    /*刪除在地圖上指定座標描點*/
    function DeleteMarker(CheckVal) {
        for (var i = 0; i < markersArray.length; i++) {
            //將所取消選取的座標與存在於地圖內陣列座標做分析
            if ($.trim(markersArray[i]["position"]) == $.trim(CheckVal)) {
                markersArray[i].setMap(null);
                markersArray[i].length = 0;
            }
        }
    }
    /*設定Marker2位置(飯店Logo)*/
    function doFirstCheck() {
        /*t1.checked = true;*/
        var Lat = "24.1607685";
        var Lng = "120.6337923";
        var contentString = "";
        createMarker2(Lat, Lng, contentString);
    }
    doFirstCheck();

    /*var control = document.getElementById('control');
    control.style.display = 'block';
    map.controls[google.maps.ControlPosition.LEFT_CENTER].push(control);*/
}
/*路線規劃起始點與終點設定*/
function calcRoute() {
    var selectedMode = document.getElementById('mode').value;

    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    var request = {
        origin: start,
        destination: end,

        travelMode: google.maps.TravelMode[selectedMode]
        /*travelMode: google.maps.TravelMode.DRIVING*/
    };
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
}
google.maps.event.addDomListener(window, 'load', initialize);