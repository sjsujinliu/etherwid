pragma solidity ^0.5.12;

contract migration {
  address public owner;
  uint public last_completed_migration;

  modifier restricted() {
    if (msg.sender == owner) _;
  }

  constructor() public {
    owner = msg.sender;
  }

  function setCompleted(uint completed) public restricted {
    last_completed_migration = completed;
  }

  function upgrade(address new_address) public restricted {
    migration upgraded = migration(new_address);
    upgraded.setCompleted(last_completed_migration);
  }
}
