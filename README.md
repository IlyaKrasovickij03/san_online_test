# san_online_test

# Содержание
- ## Описание проекта
- ## Описание функционала
- ## Применяемые технологии
- ## Подробное описание структуры проекта
- ## Подробное описание функционала приложения со скриншотами

## Описание проекта
WeatherApp - мобильное приложение для ОС Android, в котором можно узнать погоду текущего дня и пронозы на 4 следующих дня.

## Описание функционала
Функционал разработан по предоставленному ТЗ. 
- ✅Реализовать приложение состоящее из нижнего меню с 5 вкладками.
- ✅Первая вкладка - домашняя страница. Остальные вкладки - заглушки.
- ✅На домашней странице нужно отобразить список погоды за последние 5
дней. Для каждой позиции в списке нужно отобразить дату, облачность в
процентах, относительную влажность, атмосферное давление,
минимальную и максимальную температуру, скорость ветра.
- ✅При нажатии на позицию списка открывается новое окно на весь экран с  
детальной информацией для текущего дня. Информацию можно дополнить
по желанию. 
- ✅При повторном открытии приложения, если нет интернета данные должны
отображаться и должна быть возможность просмотреть детализацию
погоды для выбранного дня.
- ✅Данные о погоде загружаются для текущего местоположения пользователя.
- ✅Источник загрузки данных https://openweathermap.org/

## Применяемые технологии

### Язык
Kotlin

### Фреймворк
Jetpack Compose

### Архитектура 
В данном проекте реализована Clean Architecture в многомодульном варианте: App, Core, Data, Domain. Также была реализована архитектура MVVM в соответсвтвии с требованиями в ТЗ.

### Навигация 
Navigation Component

### Dependency Injection 
Dagger

### Многопоточность
Coroutines

### Локальное хранилище данных
DataStore

## Используемые разрешения
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

## Подробное описание структуры проекта

Скриншот всех модулей в проекте:

![image](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/d0bdaae6-99b4-4804-8bcc-b2d4e3169936)


### App

![ModuleApp (1)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/7689e06f-dac3-4663-b82a-f63960e1f8ec)

В этом модуле находится класс App: Application, вся настройка Dagger(Component, Module, Provider), а также пакет Presentation, в которой находятся все пкаеты каждого экрана и BottomBar. Пример пакета HomeScreen:

![ModuleApp (2)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/f23c4df0-5bb8-47c6-a818-7d391388841d)

### Core

![ModuleCore (1)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/8206a093-1581-4312-8eef-1eb71b217407)

В модуле Core лежит еще два модуля: Navigation и Ui. В Модуле Navigation находится центр навигации - правила навигирования:

![ModuleCore (2)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/45bd374a-5130-4b99-b46d-61ed6480eb19)

В модуле Ui находятся общие для всего приложения виджеты, тема и настройки дизайна:

![ModuleCore (3)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/93d24c15-1ae6-42a2-b5c0-ef0518495057)

### Data

![ModuleData (2)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/e53236c0-7552-4bae-84ec-87c4c7d20ff5)


В модуле Data находятся пакеты location(где мы получаем данные о текущем местоположении пользователя), mappers(мапперы данных, получаемых из сети),
network(где мы получаем данные о погоде из сети), network_monitor(где мы получаем данные о состоянии сети), repository(где мы подготавливаем данные для приложения), storage(где мы сохраняем данные в локальное хранилище)

### Domain

![ModuleDomain (1)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/e186d64c-6574-450d-be62-c42ac81c14c1)

В модуле Domain находятся пакеты location(интерфейс того, как мы получаем данные о текущем местоположении пользователя), model(entities данных для работы с ними в приложении), repository(интерфейс репозитория), usecases(основные функции, которые наше приложение выполняет с полученными данными), а также файл UseCase, в котором хранятся интерфесы видов юз-кейсов.

## Подробное описание функционала приложения со скриншотами
Как только пользователь зайдет в приложение, ему высветится диалоговое окно с просьбой разрешить геолокацию:

![Screenshot_1708183917 (4)](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/ffcf53dd-fffa-40ff-8091-f9baedb84898)

Если нажать "не разрешать", то диалоговое окно закроется и пользователь увидит следующее:

![Screenshot_1708185153](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/b1ee4b67-a674-4c33-9bd7-5217773d6068)

Нажав на кнопку "перейти в настройки", пользователя перебросит в натсройки:

![Screenshot_1708185239](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/20e18aef-b639-467d-a19e-eede3ed4ffe3)

Там пользователь может дать нашему приложению разрешение на отслеживание геолокации, затем, возвращаясь назад в наше приложение пользователь увидит экран загрузки:

![Screenshot_1708185341](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/6b7797e7-ae5c-4529-a075-4f7eaad453fb)

После пары секунд загрузки пользователю отобразится главный экран приложения с информацией о погоде в том месте, где находится пользователь:

![Screenshot_1708185407](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/8360309f-5ee7-42ef-a184-16fad2919411)

Пользователь может обновить данные о погоде, свайпнув по экрану свекху вниз:

![Screenshot_1708185803](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/46f2eaed-5272-455a-984c-b939b41d31d3)

Также пользователь может детальнее узнать информацию о погоде за конкретный день, нажав на карточку погоды:

![Screenshot_1708185910](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/0d84858a-9aec-47be-ac79-4ec0304ce559)

Пользователь имеет возможность прокручивать ленту под главной карточкой для того, чтобы узнать обо всей представленной детальной информации о погоде за этот день:

![Screenshot_1708185987](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/a3edcb7e-f9f5-47c9-a1fc-1d36218fb53a)

Также пользователь может вернуться на главный экран, где отображена погода за 5 дней, нажав на стрелочку назад в левом верхнем углу экрана:

![Screenshot_1708186116](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/0dd62a05-9a3f-48a8-abc6-7df5535017c4)

Также пользователь может навигироваться по другим экранам(заглушкам) через BottomMenu:

![Screenshot_1708186193](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/b0fc50bc-a74a-4e18-9825-d46afbd0bb30)
![Screenshot_1708186223](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/d8bcd614-e196-4257-a456-475ecf283e35)
![Screenshot_1708186254](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/3b9f5a69-4d66-4554-96b3-5383073ee73e)
![Screenshot_1708186294](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/5a49f313-1d2b-4588-b8d9-cee223afdaa9)

Если пользователь выйдет из приложения и зайдет в него с выключенной локацией, то ему будет представлена информация о погоде в по последним данным о локации пользователя(понять, что локация отключена, можно по отсутствующему значку локации в AppBar - на предыдущих скриншотах он есть):

![Screenshot_1708186409](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/e90706ac-17c6-431b-af94-32a678f753c9)

Он также может с выключенной локацией перейти к детальной информации о погоде:

![Screenshot_1708186498](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/7f0c582c-5210-4a33-b769-e2aa429b58a8)

Также, если пользователь только скачал приложение и заходит в него с выключенной локацией, то по умолчанию он увидит погоду в Москве(карточки с погодой теперь синие, потому что в Москве холодно):

![Screenshot_1708186635](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/9a211167-fc51-496d-a73c-7c29a57d2d76)

Также можно посмотреть детальнее погоду в Москве:

![Screenshot_1708186749](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/6f376798-3bbe-4ce8-b5cf-8454a76d2385)

Включив локацию и вернувшись на главный экран пользователь может обновить экран с погодой свайпнув сверху вниз:

![Screenshot_1708186845](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/316ebab1-3255-4f88-846b-909d4611cea6)
![Screenshot_1708186850](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/62f378a3-7720-4e2c-8934-1d6f8d3faa6a)

Если пользователь закроет приложение или выйдет из него, выключит интернет и снова зайдет в приложение, ему будут отображаться последние полученные данные о погоде(понять, что интернет отсутсвует, можно по соответствующему значку в AppBar):

![Screenshot_1708186994](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/bbaa1988-43e8-484d-8759-ddf83db30dc9)

Также в этом случае пользователь может изучить детально погоду в конкретный день:

![Screenshot_1708187091](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/faa32337-3f98-428a-9141-0d009185854e)

Также рассмотрим ситуацию, когда пользователь впервые заходит в приложение и одобряет доступ к отслеживанию его геолокации, чтобы убедиться, что большинство краевых случаев предусмотрено:

![Screenshot_1708187276](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/870cc97c-3d0c-4b54-bd91-ffdac5080cc6)

Если при появлении такого окна пользователь нажмет на пустое место экрана, то ему высветится диалоговое окно:

![Screenshot_1708187335](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/1e1b3ca9-4189-4820-b4a8-59047c73ddc4)

Если пользователь нажмет мимо диалогового окна или на кнопку "отменить", то мы увидим знакомый экран, на котором мы просим пользователя перейти в настройки:

![Screenshot_1708187423](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/b82c54cc-081c-4f31-ad59-5e929a0876a6)

Если же пользователь нажмет "разрешить", он снова увидит системное диалоговое окно о предоставлении доступа к геолокации:

![Screenshot_1708187488](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/db8b3492-f372-41ff-9068-f3a348c445d4)

Если в системном окне пользователь разрешит нашему приложению доступ к геолокации, то мы увидим экран загрузки и затем главный экран приложения с погодой за 5 дней:

![Screenshot_1708187583](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/c44b5c93-3a54-43a8-be12-a349d0852c4e)
![Screenshot_1708187589](https://github.com/IlyaKrasovickij03/san_online_test/assets/145861478/210364b7-0634-4e55-bb94-19992479c02b)

