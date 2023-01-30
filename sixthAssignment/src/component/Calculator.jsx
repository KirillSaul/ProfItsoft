import React from 'react';
import CalculatorButton from "./CalculatorButton";
import CalculatorOutput from "./CalculatorOutput";
import {Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";

class Calculator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            output: 0,
            firstNumber: null,
            secondNumber: null,
            operation: null,
            result: null,
            history: [],
        }
    }

    changeOutput = (buttonName) => {
        if (this.state.output === 0 && this.state.firstNumber === null) {
            this.setState({output: ""})
        }
        this.addOutputValue(buttonName)


        if (buttonName >= "0" && buttonName <= "9") {
            if (this.state.operation === null) {
                this.setFirstNumber(buttonName)
            } else {
                this.setSecondNumber(buttonName)
            }

        } else {
            if (buttonName === "=") {
                this.count();
            } else {
                if (this.state.secondNumber === null) {
                    this.setState({operation: buttonName});
                } else {
                    this.count();
                }
            }
        }
    }

    setFirstNumber(number) {
        this.setState((state) => ({firstNumber: Number(state.firstNumber) + number}));
    }

    setSecondNumber(number) {
        this.setState((state) => ({secondNumber: Number(state.secondNumber) + number}));
    }

    setResult(value) {
        this.setState({result: value});

    }

    addOutputValue(value) {
        this.setState((state) => ({output: state.output + value}));
    }

    addHistory() {
        this.setState((state) => ({history: this.state.history.concat(state.output + state.result)}));
    }

    refreshState() {
        this.setState((state) => (
            {
                firstNumber: state.result,
                secondNumber: null,
                operation: null,
                result: null,
            }
        ));
    }

    refreshOutput() {
        this.setState((state) => ({output: state.result}));
    }

    count() {
        switch (this.state.operation) {
            case "+":
                this.setResult(Number(this.state.firstNumber) + Number(this.state.secondNumber))
                break;
            case "-":
                this.setResult(Number(this.state.firstNumber) - Number(this.state.secondNumber))
                break;
            case "*":
                this.setResult(Number(this.state.firstNumber) * Number(this.state.secondNumber))
                break;
            case "/":
                this.setResult(Number(this.state.firstNumber) / Number(this.state.secondNumber))
                break;
        }
        this.addHistory();
        this.refreshOutput();
        this.refreshState();
    }

    render() {
        return <div>
            {this.state.history.map((value) => <Grid>{value}</Grid>)}
            <Grid>
                <CalculatorOutput output={this.state.output}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="+" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="-" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="*" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="/" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="=" changeOutput={this.changeOutput}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="7" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="8" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="9" changeOutput={this.changeOutput}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="4" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="5" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="6" changeOutput={this.changeOutput}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="1" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="2" changeOutput={this.changeOutput}/>
                <CalculatorButton buttonName="3" changeOutput={this.changeOutput}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="0" changeOutput={this.changeOutput}/>
            </Grid>
        </div>
    }
}

export default Calculator;