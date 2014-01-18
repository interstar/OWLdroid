// Generated by CoffeeScript 1.4.0
(function() {

  this.NavHistory = (function() {

    function NavHistory() {
      this.past = [];
      this.future = [];
    }

    NavHistory.prototype.newForward = function(currentPageName) {
      this.past.push(currentPageName);
      return this.future = [];
    };

    NavHistory.prototype.back = function() {
      var currentName;
      if (this.past.length === 1) {
        return this.past[0];
      }
      currentName = this.past.pop();
      this.future.push(currentName);
      return this.past[this.past.length - 1];
    };

    NavHistory.prototype.forward = function() {
      var pageName;
      if (this.future.length === 0) {
        return this.past[this.past.length - 1];
      }
      pageName = this.future.pop();
      this.past.push(pageName);
      return pageName;
    };

    return NavHistory;

  })();

}).call(this);