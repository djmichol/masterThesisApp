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

app.directive("editorDirective", function() {
    return {
      restrict: 'E',
	  transclude: true,
      scope: { content:'=content' },
      templateUrl: "view/editorContent.html"
    };
});