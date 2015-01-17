$(document).ready(function() {
	
    $.ajax({
        url: "/sample/user/add?Test1"
    }).then(function(data) {
       $('.user-add1-success').append(data.success);
       $('.user-add1-message').append(data.message);
    });
    
    $.ajax({
        url: "/sample/user/add?Test2"
    }).then(function(data) {
       $('.user-add2-success').append(data.success);
       $('.user-add2-message').append(data.message);
    });
    
    $.ajax({
        url: "/sample/user/add?Test3"
    }).then(function(data) {
       $('.user-add3-success').append(data.success);
       $('.user-add3-message').append(data.message);
    });
    
	
    $.ajax({
        url: "/sample/user/show"
    }).then(function(data) {
       $('.user-success').append(data.success);
       $('.user-message').append(data.message);
    });
    
    
});