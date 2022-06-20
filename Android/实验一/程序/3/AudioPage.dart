import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/material.dart';

class AudioPage extends StatefulWidget {

  @override
  _AudioPage createState() => _AudioPage();

}

class _AudioPage extends State<AudioPage> {

  AudioCache audioCache = AudioCache(prefix: 'assets/audio/');
  AudioPlayer audioPlayer = AudioPlayer();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
        RaisedButton(
            child: Text("start"),
            onPressed: () {
              audioCache.fixedPlayer = audioPlayer;
              audioCache.play('sound.mp3');
            }
        ),
          RaisedButton(
              child: Text("continue"),
              onPressed: () {
                audioCache.fixedPlayer = audioPlayer;
                audioCache.play('sound.mp3');
              }
          ),
          RaisedButton(
              child: Text("pause"),
              onPressed: () {
                audioCache.fixedPlayer = audioPlayer;
                audioPlayer.pause();
              }
          ),
          RaisedButton(
              child: Text("stop"),
              onPressed: () {
                audioCache.fixedPlayer = audioPlayer;
                audioPlayer.stop();
              }
          ),
      ],
      )
    );
  }

}