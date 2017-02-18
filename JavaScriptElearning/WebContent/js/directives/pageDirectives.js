//gorne menu
app.directive("menuListDirective", function() {
    return {
      restrict: 'E',
      templateUrl: "view/menu.html"
    };
});
 
//stopka
app.directive("footerDirective", function() {
    return {
      restrict: 'E',
      templateUrl: "view/footer.html"
    };
});

//banner
app.directive("bannerDirective", function() {
    return {
      restrict: 'E',
	  transclude: true,
      scope: { name:'@' },
      templateUrl: "view/banner.html"
    };
});

app.directive("pathCardDirective", function() {
    return {
      restrict: 'E',
	  transclude: true,
      scope: { path:'=path' },
      templateUrl: "view/pathCard.html"
    };
});

app.directive("loader", function ($rootScope) {
    return function ($scope, element, attrs) {
        $scope.$on("loader_show", function () {
            return jQuery('#loaderDiv').show();
        });
        return $scope.$on("loader_hide", function () {
            return jQuery('#loaderDiv').hide();
        });
    };
});

app.directive("editorDirective", function() {
    return {
      restrict: 'E',
	  transclude: true,
      scope: { content:'=content' },
      templateUrl: "view/editorContent.html"
    };
});