# Weather

#### An android that shows the weather of your location and in 10 different European Cities.

#### By **Bartholomew Kelly Egesa**

## Description

An android application that gets your location and shows the weather of your location and in 10 different European Cities which are Libon, Madrid, Berlin, Copenhagen, Rome, London, Dublin, Prague and Vienna.

## Behavior Driven Development

| BEHAVIOR:Our program should handle      |  Input Example When it receives   |                                           Output Example It should return                                            |
| --------------------------------------- | :-------------------------------: | :------------------------------------------------------------------------------------------------------------------: |
| Click on accept permissions             |    click on accept permissions    |                               A prompt to allow weather to access permission is shown                                |
| Click on switch on gps                  |      click on switch on gps       |                                  A setting's intent to switch on location is shown                                   |
| Click on a city after application loads | Inputs:-Click on "Vienna, Austria | Sends a get request to openweather api and retrieves the week weather information and navigates to the next activity |
| Scroll on the weather activity          |          Inputs:-Scroll           |                  Will display the weather forecast from the current till next week on the same date                  |

## Known Bugs

There are currently no known bugs

## Setup/Installation Requirements

- Setup git
- Open the terminal application by either clicking on the terminal icon or by clicking Ctrl + alt + T.
- Create a new folder called weather by pressing mkdir weather and pressing enter.
- Navigate to weather by pressing cd weather and pressing enter.
- Go to KellyEgesa github user name on the browser, click on repositories, Click on weather then click on clone or download option
- Copy paste the given Url
- Press git clone plus the url on the terminal then press center.
- Inorder to run the application one needs to retrieve their own individual access tokens on https://openweathermap.org/api . It is then placed in gradle.properties as "OpenWaetherAccessToken:**_Your individual api Key_**"

## Technologies Used

### DEVELOPMENT

- JAVA
- ANDROID
- XML

### APIS CALL

- RETROFIT

## Support and contact details

You can contact me via Email at kelly.egesa@gmail.com or via +254726359282.

### License

_M.I.T_
Copyright (c)2021 **KELLY EGESA**
