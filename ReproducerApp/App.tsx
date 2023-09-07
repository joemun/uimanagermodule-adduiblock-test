/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  Button,
  NativeModules,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import RTNCalculator from 'rtn-calculator/js/NativeCalculator';

import {Colors, Header} from 'react-native/Libraries/NewAppScreen';

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Button
            title="Call Turbo Module"
            onPress={async () => {
              console.log('Calling turbo module...');
              const value = await RTNCalculator?.add(3, 7);
              console.log('Result: ', value);
            }}
          />
          <Button
            title="Call Legacy Module"
            onPress={async () => {
              console.log('Calling legacy module...');
              NativeModules.LegacyModule.hello(
                'someArg',
                (error: any, result: string) => {
                  console.log('Callback invoked!');
                },
              );
            }}
          />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
