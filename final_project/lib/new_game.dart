import 'dart:ui';

import 'package:flutter/material.dart';

enum Difficulty{
    easy,
    medium,
    hard
}

class NewGamePage extends StatelessWidget {
  const NewGamePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        title: const Text("New Game"),
        backgroundColor: Colors.transparent,
        flexibleSpace: ClipRect(
          child: BackdropFilter(
            filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10), // Blur effect
            child: Container(
              color: Colors.black.withOpacity(0.15), // Slight backround color to the appbar
            ),
          ),
        ),
      ),
      body: Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [Colors.orange, Colors.blue],
          ),
        ),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                "Select Difficulty",
                style: TextStyle(
                  fontSize: 24.0,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
              const SizedBox(height: 40),
              ElevatedButton(
                onPressed: () {
                  _loadGame(context, Difficulty.easy);
                },
                style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.black,
                  backgroundColor: Colors.white,
                  padding:
                      const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                ),
                child: const Text('Easy'),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  _loadGame(context, Difficulty.medium);
                },
                style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.black,
                  backgroundColor: Colors.white,
                  padding:
                      const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                ),
                child: const Text('Medium'),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  _loadGame(context, Difficulty.hard);
                },
                style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.black,
                  backgroundColor: Colors.white,
                  padding:
                      const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                ),
                child: const Text('Hard'),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _loadGame(BuildContext context, Difficulty difficulty) {
    if (difficulty == Difficulty.easy){
      
    } else if (difficulty == Difficulty.medium){
      
    } else if (difficulty == Difficulty.hard){
      
    }
    // TODO Load correctly
    print('Loading game with difficulty: ${difficulty.name}');
  }
}