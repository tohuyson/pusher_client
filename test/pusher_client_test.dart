import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:pusher_client/pusher_client.dart';
import 'dart:developer' as developer;

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();
  MethodChannel channel =
      const MethodChannel('com.github.chinloyal/pusher_client');

  group('PusherClient Test | ', () {
    setUp(() {
      TestDefaultBinaryMessengerBinding.instance!.defaultBinaryMessenger
          .setMockMethodCallHandler(channel, (call) {
        print(call.method);
        switch (call.method) {
          case 'init':
            return null;
          case 'connect':
            return null;
          default:
            throw UnimplementedError();
        }
      });
    });

    test('Pusher client returns a singleton', () {
      var pusher1 = PusherClient('key', PusherOptions());
      var pusher2 = PusherClient('key', PusherOptions());

      expect(pusher1.hashCode, pusher2.hashCode);
    });

    // test()
  });
}
