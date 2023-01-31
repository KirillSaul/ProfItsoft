import React from 'react';
import ButtonNumber from "./ButtonNumber";
import {Box, Grid} from "@material-ui/core";
import CalculatorOutput from "./CalculatorOutput";
import OutsideButton from "./OutsideButton";
import ButtonOperation from "./ButtonOperation";
import ButtonResult from "./ButtonResult";

class Calculator extends React.Component {
    constructor(props) {
        super(props);
        this.outsideButtonClick = this.outsideButtonClick.bind(this);
        this.buttonNumberClick = this.buttonNumberClick.bind(this);
        this.buttonOperationClick = this.buttonOperationClick.bind(this);
        this.buttonResultClick = this.buttonResultClick.bind(this);
        this.state = {
            output: 0,
            firstNumber: null,
            secondNumber: null,
            operation: null,
            result: null,
            history: [],
        }
    }

    async buttonNumberClick(buttonName) {
        if (this.state.output === 0 && this.state.firstNumber === null) {
            await this.setState({output: ""})
        }
        if (this.state.operation === null) {
            await this.addFirstNumber(buttonName)
        } else {
            await this.addSecondNumber(buttonName)
        }
        await this.addOutputValue(buttonName)
    }

    async buttonOperationClick(buttonName) {
        if (this.state.secondNumber !== null) {
            await this.count(buttonName);
        } else if (this.state.operation !== null) {
            await this.setState((state) => (
                {
                    output: state.output.substring(0, state.output.length - 1) + buttonName
                }
            ));
        } else {
            await this.setOperation(buttonName)
            await this.addOutputValue(buttonName)
        }
    }

    async buttonResultClick() {
        await this.count("=");
    }

    async outsideButtonClick() {
        const response = await fetch("http://localhost:8081/math/expamples?count=5", {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        });
        if (response.ok) {
            const examples = await response.json()
            for (let example of examples) {
                for (let symbol of example) {
                    if (symbol >= "0" && symbol <= "9") {
                        await this.buttonNumberClick(symbol)
                    } else {
                        await this.buttonOperationClick(symbol)
                    }
                }
                await this.buttonResultClick()
                await this.setState({firstNumber: null});
                await this.setOperation(null)
                await this.setState({output: ""});
            }
        }
    }

    addFirstNumber(number) {
        this.setState((state) => ({firstNumber: Number(state.firstNumber) + number}));
    }

    addSecondNumber(number) {
        this.setState((state) => ({secondNumber: Number(state.secondNumber) + number}));
    }

    setOperation(operation) {
        this.setState({operation: operation});
    }

    setResult(value) {
        this.setState({result: value});

    }

    addOutputValue(value) {
        this.setState((state) => ({output: state.output + value}));
    }

    addHistory() {
        this.setState((state) => (
            {
                history: this.state.history.concat(state.output + "=" + state.result)
            }
        ));
    }

    refreshState(operation) {
        this.setState((state) => {
                const commonState = {
                    firstNumber: state.result,
                    secondNumber: null,
                    result: null
                }
                if (operation !== "=") {
                    return {
                        operation: operation,
                        output: state.result + operation,
                        ...commonState
                    }
                } else {
                    return {
                        output: state.result,
                        operation: null,
                        ...commonState
                    }
                }
            }
        );
    }

    async count(operation) {
        switch (this.state.operation) {
            case "+":
                await this.setResult(Number(this.state.firstNumber) + Number(this.state.secondNumber))
                break;
            case "-":
                await this.setResult(Number(this.state.firstNumber) - Number(this.state.secondNumber))
                break;
            case "*":
                await this.setResult(Number(this.state.firstNumber) * Number(this.state.secondNumber))
                break;
            case "/":
                if (Number(this.state.secondNumber) === 0) {
                    await this.setResult("Error division by zero")
                } else {
                    await this.setResult(Number(this.state.firstNumber) / Number(this.state.secondNumber))
                }
                break;
        }
        await this.addHistory();
        await this.refreshState(operation);
    }

    render() {
        return <div>
            <div>
                {
                    this.state.history.map((value, index) => {
                            if (index === (this.state.history.length - 1)) {
                                return (
                                    <Grid key={index}>
                                        <Box fontWeight="fontWeightBold">
                                            {value}
                                        </Box>
                                    </Grid>
                                )
                            } else {
                                return <Grid key={index}>{value}</Grid>
                            }
                        }
                    )
                }
            </div>
            <Grid>
                <CalculatorOutput output={this.state.output}></CalculatorOutput>
            </Grid>
            <Grid>
                <ButtonOperation buttonName="+" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="-" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="*" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="/" onClick={this.buttonOperationClick}/>
                <ButtonResult buttonName="=" onClick={this.buttonResultClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="7" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="8" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="9" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="4" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="5" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="6" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="1" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="2" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="3" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="0" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <Box mt={3}>
                    <OutsideButton buttonName="Получить и решить примеры" onClick={this.outsideButtonClick}/>
                </Box>
            </Grid>
        </div>
    }
}

export default Calculator;