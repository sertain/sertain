<p align="center">
  <a href="#">
    <img alt="Sertain" src="https://i.imgur.com/zd0squD.png" />
  </a>
</p>

<h4 align="center">
  Write more for your robot with less, and be absolutely SERTain that your code works.
</h4>

<p align="center">
  <a href="https://travis-ci.org/sertain/sertain">
    <img src="https://img.shields.io/travis/sertain/sertain/master.svg?style=flat-square" />
  </a>
  <a href="https://gitter.im/sertain/development">
    <img src="https://img.shields.io/gitter/room/sertain/development.js.svg?style=flat-square" />
  </a>
  <a href="https://jitpack.io/#sertain/sertain">
    <img src="https://img.shields.io/jitpack/v/sertain/sertain.svg?style=flat-square" />
  </a>
  <a href="LICENSE">
    <img src="https://img.shields.io/github/license/sertain/sertain.svg?style=flat-square" />
  </a>
  <a href="https://github.com/sertain/sertain/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/sertain/sertain.svg?style=flat-square" />
  </a>
</p>

***Sertain legacy is no longer supported. Check out the new version [here](https://github.com/SouthEugeneRoboticsTeam/sertain)!***

## Installation

Sertain is hosted on JitPack and only available through the Gradle build system. First, add JitPack
to your root build.gradle at the end of `repositories`:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

And then add the dependency:

```groovy
dependencies {
    compile 'org.sert2521.sertain:core:1.2.1'
}
```

## Documentation

Checkout the [documentation](https://sertain.github.io/javadocs/) for information on how to use
Sertain on your robot, and for a complete list of features. The javadocs are automatically
generated after each push to master.
