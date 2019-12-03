var inbox = artifacts.require("./inbox.sol");

module.exports = function(deployer) {
  deployer.deploy(inbox);
};
