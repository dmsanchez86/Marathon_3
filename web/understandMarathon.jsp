<%-- 
    Document   : understandMarathon
    Created on : 13/03/2016, 12:15:24 AM
    Author     : Mauro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Undertand Marathon - Marathon Skills 2016</title>
        <link rel="stylesheet" href="stylesheets/main.css">
    </head>
    <body>
        <h1 class="title">Marathon Skills 2016</h1>
        <h2 class="subtitle">
            Understanding Marathon<br>
            ¿How long is a Marathon?
        </h2>
        <main>
            <section>
                <div class="center mid-padding">
                    <h3 class="title-element">
                        Bus
                    </h3>
                </div>
                <div class="container-img center mid-padding">
                    <img src="images/bus.jpg" class="img-element"/>
                </div>
                <div class="center mid-padding">
                    <p class="description-element">The length Bus is 10m. Need 4200 from them to cover track 42km of marathon.</p>
                </div>
            </section>
            <section class="tabs-content">
                <ul class="tabs">
                    <li target="#velocity" class="active">Velocity</li>
                    <li target="#distance">Distance</li>
                </ul>
                <div class="container-tabs">
                    <div class="tab active" id="velocity">
                        <div class="item" ref="73">
                            <img src="images/airbus-a380.jpg"/>
                            <label>Airbus A380</label>
                        </div>
                        <div class="item" ref="0.245">
                            <img src="images/pair-of-havaianas.jpg"/>
                            <label>Havaianas</label>
                        </div>
                        <div class="item" ref="105">
                            <img src="images/football-field.jpg"/>
                            <label>Football Field</label>
                        </div>
                        <div class="item" ref="1.81">
                            <img src="images/ronaldinho.jpg"/>
                            <label>Ronaldinho</label>
                        </div>
                        <div class="item active" ref="10">
                            <img src="images/bus.jpg"/>
                            <label>Bus</label>
                        </div>
                    </div>
                    <div class="tab" id="distance">
                        <div class="item" ref="345">
                            <img src="images/f1-car.jpg"/>
                            <label>F1 Car</label>
                        </div>
                        <div class="item" ref="0.03">
                            <img src="images/worm.jpg"/>
                            <label>Worm</label>
                        </div>
                        <div class="item" ref="0.12">
                            <img src="images/sloth.jpg"/>
                            <label>Sloth</label>
                        </div>
                        <div class="item" ref="35">
                            <img src="images/capybara.jpg"/>
                            <label>Capybara</label>
                        </div>
                        <div class="item" ref="80">
                            <img src="images/jaguar.jpg"/>
                            <label>Jaguar</label>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        
        <footer class="transparent">
            <button type="button" name="btnBack" onclick="location.href='./index.jsp'">Back</button>
        </footer>
        
        <script src="js/actions.js"></script>
    </body>
</html>
