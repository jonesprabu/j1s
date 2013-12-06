// JavaScript Document
$(document).ready(function(){
	
	//bottom Hold function
	
	$('.bottomHold').hover(function (){
		
		var thisSect = $(this).find('.btmMenuVisCont');
		var findArwUp = $(this).find('.btmPanelArwUp');
		var findArwDown = $(this).find('.btmPanelArwDown');
		 if ($(".btmMenuVisCont").is(":hidden")) {
         $(this).find('.btmMenuVisCont').slideDown('fast');
		 findArwUp.addClass('btmPanelArwDown').removeClass('btmPanelArwUp');
    } else {
        thisSect.slideUp('fast');
		findArwDown.addClass('btmPanelArwUp').removeClass('btmPanelArwDown');
    }
	});
	
	
	//contact page tab function
	$('.contactTabs li').click(function(){
		$('.contactTabs').find('.active').removeClass('active');
		$('.contactTabContent').find('.tabC').hide();
		$(this).addClass('active');
		var thisVal = $(this).attr('name');
		$('#'+thisVal).show();
	});
	
	
});