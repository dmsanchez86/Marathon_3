'use strict';

var text = {
    velocity: "The maxima velocity the #element# is #ref#km/h. Need #result# to complete this 42km of marathon.",
    distance: "The length #element# is #ref#m. Need #result# from them to complete one 42km of marathon."
};

window.onload = function(){
    var title_element = document.querySelector(".title-element");
    var img_element = document.querySelector(".img-element");
    var description_element = document.querySelector(".description-element");
    
    var menutabs = document.querySelectorAll('.tabs li');
    var tabs = document.querySelectorAll('.tab');
    var items = document.querySelectorAll('.item');
    
    for (var i = 0; i < menutabs.length; i++) {
        menutabs[i].onclick = function(){
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].className = "tab";
            }
            
            for (var i = 0; i < menutabs.length; i++) {
                menutabs[i].className = "";
            }
            
            var target = this.getAttribute('target');
            
            if(target === "#velocity"){
                items[4].click();
            }else{
                items[9].click();
            }
            
            this.className = "active";
            document.querySelector(target).className = "tab active";
        }
    }
    
    for (var i = 0; i < items.length ; i++) {
        items[i].onclick = function(e){
            for (var i = 0; i < items.length ; i++) {
                items[i].className = "item";
            }
            
            var ref = this.getAttribute('ref');
            
            this.className = "item active";
            
            img_element.src = this.children[0].src;
            title_element.innerHTML = this.children[1].innerHTML;
            
            try{
                var tabActive = document.querySelector(".tab.active");
            
                if(tabActive.id === "distance"){
                    description_element.innerHTML = text.velocity
                                                                .replace("#element#", this.children[1].innerHTML)
                                                                .replace("#ref#", ref)
                                                                .replace("#result#", calculateVelocity(ref));
                }else{
                    description_element.innerHTML = text.distance
                                                                .replace("#element#", this.children[1].innerHTML)
                                                                .replace("#ref#", ref)
                                                                .replace("#result#", calculateDistance(ref));
                }
            }catch (e){}
        }
    }
    
    var marathonLength = 42000;
    
    function calculateVelocity(velocity){  
        
        var time = Math.round((42 * 3600) / (velocity));
        console.log(time);
        var thoras = 0.0;
        var minutes = 0.0;
        var seconds = 0.0;
        var resto = 0.0;
        if (time > 3600) {
            resto = time % 3600;
            time -= resto;
            thoras = Math.round(time / 3600);
            time = resto;
        }
        if (time > 60) {
            resto = time % 60;
            time -= resto;
            minutes = Math.round(time / 60);
            time = resto;
        }
        seconds = time;
        
        return minutes+"minutes and "+seconds+"seconds ";
        
    }
    
    function calculateDistance(ref){
        var distance =marathonLength / parseFloat(ref);
        return distance.toFixed(2);
    }
}

