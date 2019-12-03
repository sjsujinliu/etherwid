var transfer = artifacts.require("./transfer.sol");

module.exports = function(deployer) {
  deployer.deploy(transfer);
};
