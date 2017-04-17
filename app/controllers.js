var apka = angular.module('apka', ['ngRoute','ngCookies']);

apka.controller('kontrolerZaloguj',fKontrolerZaloguj);
function fKontrolerZaloguj($scope,$http,$cookies,$location) {

  if($cookies.log==1) $scope.zalogowany="Jesteś już zalogowany! Przejdź do <a href='#!'>mapy</a>";
  
  $scope.zaloguj = function() {
  
    var dataObj = {
      email : $scope.login,
      pass : $scope.pswd,
    };
        
    var res = $http.post('http://httpbin.org/post', dataObj);
    res.then(function(odp) {
      $scope.msg = odp;
      var logCookie = $cookies.log;
      $cookies.log = 1;
      $cookies.login=$scope.login;
      $location.path('mapa');
    }); 
    
  }
}

apka.controller('kontrolerRejestracja',fKontrolerRejestracja);
function fKontrolerRejestracja($scope,$http,$cookies) {

  if($cookies.log==1) $scope.zalogowany="Jesteś już zalogowany! Przejdź do <a href='#!'>mapy</a>";
  
  $scope.zarejestruj = function() {
		
     var dataObj = {
        login : $scope.login,
        mail : $scope.mail,
        pswd1 : $scope.pswd1,
        pswd2 : $scope.pswd2,
    };
			
		var res = $http.post('http://httpbin.org/post', dataObj);
		res.then(function(odp) {
      $scope.msg = odp;
      
    });
      
      
  }
      
}


apka.controller('kontrolerMapa',fKontrolerMapa);
function fKontrolerMapa($scope,$http,$cookies,$location) {

  if($cookies.log==1) $scope.login=$cookies.login;
  else $location.path('zaloguj');

$scope.wyloguj = function() {
      $cookies.log = 0;
      $cookies.login="";
      $location.path('zaloguj');
}

}