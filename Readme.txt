Kotlin/Js REACT app

-using kotlin DSL features and libraries, allow us to manipulate html elements, CSS, and React components similarly to any
other objects in kotlin. Allows use of kotlin features and syntax to integrate seamlessly with these elements
-separate DSLs for html, css, react*
    kotlin-styled provides wonderful typesafe wrappers for styled-components that allow us to quickly and safely define
    styles globally or for individual elements of our DOM.It wraps the styled-components library and allows us to build
    constructs that look like CSS-in-JS

-Pulls from remote site/simple api to get links to videos of a conference. Chagnes state when video is watched
-Added components ported form react to allow sharing videos etc
-Added deployment to heroku - kotlin requires jvm to be present on compiling system (unlike JS) so need to add
    heroku buildpacks:add -i 1 https://github.com/heroku/heroku-buildpack-jvm-common.git
    (option) heroku buildpacks:add https://github.com/mars/create-react-app-buildpack.git
    then git push heroku master - to create new release using those buildpacks