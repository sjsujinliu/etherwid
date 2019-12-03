var ipfs_inbox = artifacts.require("./ipfs_inbox.sol");

module.exports = function(deployer) {
  deployer.deploy(ipfs_inbox);
};
