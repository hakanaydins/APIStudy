@trelloApi
Feature: Kullanıcı Trello API uzerinde cesitli islemler yapabilmelidir
  Background: Kullanıcı Base URL i setler
    Given Kullanıcı Trello icin Base URL set eder "https://api.trello.com"
     #Bu scen. de bizler board create edeceğiz ayrıca taskımıza bizlere verilen
    # diğer işlemleri gerçekleştirebilmek için bizlerin board id sine ihtiyacı vardır
    #Board ıd yi de bu scen. alabacağız. Ve Boardun create edildiğini assert edeceğiz.    ------->>>>>>>>>< class seviyesinde tanımla
  Scenario: Kullanıcı board create edebilmeli
    When Kullanıcı board create edebilmek icin ilgili url e POST methodu ve ilgili endpointler ile request atar "idPath","1","boardsPath","boards"
    And Kullanıcı board id yi alır
    Then Kullanıcı boad un basarili bir sekilde create edildigini dogrular
