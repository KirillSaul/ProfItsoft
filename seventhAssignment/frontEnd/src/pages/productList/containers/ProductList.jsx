import {useEffect, useState} from "react";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';
import Link from 'components/Link'
import {useDispatch, useSelector} from "react-redux";
import productActions from "../../productList/store/actions/actions";
import useAccessValidate from "../../../hooks/useAccessValidate";

const ProductList = ({authorities}) => {
    const [productList, setProductList] = useState([]);

    const canSeeList = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    });

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(productActions.getProducts())
    }, [])

    const products = useSelector((state) => {
        return state.reducer.products
    })

    useEffect(() => {
        setProductList(() => ([...products]));
    }, [products])


    return (
        <div>
            {canSeeList &&
                <div>
                    <Button variant="contained" color="primary" href="/createEditProduct">
                        Create
                    </Button>
                    <div> {
                        productList.map(value =>
                            (
                                <Grid>
                                    <Box sx={{m: 2}}/>
                                    {value.name}
                                    <Link to={"/createEditProduct/" + value.id}>
                                        <Button variant="contained" color="primary">
                                            Edit
                                        </Button>
                                    </Link>
                                    <Button variant="contained" color="secondary"
                                            onClick={() => dispatch(productActions.deleteProductById(value.id))}>
                                        Delete
                                    </Button>
                                </Grid>
                            )
                        )
                    }
                    </div>
                </div>
            }
        </div>
    )

}
export default ProductList