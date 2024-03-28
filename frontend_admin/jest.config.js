module.exports = {
  preset: '@vue/cli-plugin-unit-jest',
  transform: {
    '^.+\\.js$': 'babel-jest',
  },
  moduleNameMapper: {
    "^@/(.*)$": "<rootDir>/src/$1",
    '^pinia$': '<rootDir>/node_modules/pinia',
    '^axios$': '<rootDir>/node_modules/axios',
  },
  transformIgnorePatterns: [
    '^/node_modules/(?!axios)',
    '^/node_modules/(?!pinia)',
  ],
  reporters: [
    'default',
    ['jest-junit', {outputDirectory: 'test-results', outputName: 'report.xml'}],
  ],
}
