import React from "react";
import "./App.css";

class TodoItemCheck extends React.Component {
  handleSubmit(event) {
    console.log("handleSubmit");
    return false;
  }

  render() {
    if (this.props.checked === "true") {
      return (
        <form action="#" onSubmit={this.handleSubmit}>
          <input type="hidden" value={this.props.id} name="id" />
          <input type="hidden" value={this.props.content} name="content" />
          <input type="hidden" value={this.props.checked} name="checked" />
          <input
            style={{ margin: "5px 0 0 0" }}
            className="btn btn-sm btn-primary"
            type="submit"
            value="Uncheck"
          />
        </form>
      );
    } else {
      return (
        <form action="#" onSubmit={this.handleSubmit}>
          <input type="hidden" value={this.props.id} name="id" />
          <input type="hidden" value={this.props.content} name="content" />
          <input type="hidden" value={this.props.checked} name="checked" />
          <input
            style={{ margin: "5px 0 0 0" }}
            className="btn btn-sm btn-primary"
            type="submit"
            value="Check"
          />
        </form>
      );
    }
  }
}

class TodoItemCreate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      content: "",
      checked: false
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    console.log("handleSubmit");
    console.log(this.state.content);

    fetch("http://localhost:8080/items", {
      method: "post",
      headers: {
        Accept: "application/json, text/plain",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ content: this.state.content, checked: false })
    })
      .then(res => {
        console.log(res);
        return res.json;
      })
      .then(res => {
        console.log(res);
        window.location.reload();
      });

    return false;
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });

    return false;
  }

  render() {
    return (
      <form action="#" onSubmit={this.handleSubmit}>
        <input
          style={{ margin: "0 0 5px 0" }}
          className="form-control"
          type="text"
          name="content"
          onChange={this.handleChange}
        />
        <input
          style={{ margin: "5px 0 0 0" }}
          className="btn btn-primary"
          type="submit"
          value="Create"
        />
      </form>
    );
  }
}

class TodoItemEdit extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    console.log(event.target.value);
  }

  render() {
    return (
      <form action="#" onSubmit={this.handleSubmit}>
        <input
          style={{ margin: "20px 0 5px 0" }}
          className="form-control"
          type="text"
          value={this.props.content}
        />
        <input
          className="btn btn-primary btn-block"
          type="submit"
          value="Edit"
        />
      </form>
    );
  }
}

class TodoItemDelete extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    console.log(event.target.value);
  }

  render() {
    return (
      <form action="#" onSubmit={this.handleSubmit}>
        <input
          style={{ margin: "5px 0 0 0" }}
          className="btn btn-danger btn-block"
          type="submit"
          value="Delete"
        />
      </form>
    );
  }
}

class TodoItem extends React.Component {
  render() {
    return (
      <div className="col-6">
        <div style={{ margin: "10px 0", padding: "10px" }} className="card">
          <h5 className={"card-title"}>
            {this.props.id} <TodoItemCheck checked={this.props.checked} />
          </h5>
          <p className={"card-text"}>{this.props.content}</p>
          <TodoItemEdit />
          <TodoItemDelete />
        </div>
      </div>
    );
  }
}

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      todoItemList: [],
      test: "hello",
      componentList: []
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/items")
      .then(res => res.json())
      .then(res => {
        this.setState({
          todoItemList: res
        });
        console.log(this.state.todoItemList[0].checked);
      });
  }

  render() {
    let thing = this.state.todoItemList.map(item => (
      <TodoItem
        key={item.id}
        id={item.id}
        content={item.content}
        checked={item.checked.toString()}
      />
    ));
    console.log(thing);
    return (
      <div className={{ App }}>
        <div className="container">
          <div className="row">
            <div className={"col-12"}>
              <TodoItemCreate />
            </div>
            {thing}
          </div>
        </div>
      </div>
    );
  }
}

export default App;
